package com.example.api.Routes.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.api.Routes.Fdt.Fdt;
import com.example.api.Routes.Fdt.FdtRepository;
import com.example.api.Routes.Nap.Nap;
import com.example.api.Routes.Nap.NapRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PosService {
    
    private PosRepository posRepository;
    private NapRepository napRepository;
    private FdtRepository fdtRepository;

    public List<Pos> getAllPoss(){
        return posRepository.findAll();
    }

    public List<ResponseEntity<String>> addAllNewPos(String srt){
        List<ResponseEntity<String>> response = new ArrayList<>();
        String[] data = srt.split("\n");
        for (String string : data) {
            response.add(addNewPos(string));
        }
        return response;
    }

    public ResponseEntity<String> addNewPos(String value) {
        try {
            String srt = normalizarPArtes(value.toUpperCase());
            System.out.println("dato normalizado: "+srt);
            String[] data = getRoutes(srt);
            if(data.length == 4){
                Optional<Fdt> optionalFdt = fdtRepository.findByCod(data[0] + "-"+data[1]);
                if (optionalFdt.isPresent()) {
                    Fdt fdt = optionalFdt.get();
                    System.out.println(data[2] + "-"+fdt.getId_fdt());
                    Optional<Nap> optionalNap = napRepository.findByCodAndFdt(data[2], fdt);
                    System.out.println(data[2] + "-"+fdt.getId_fdt());
                    if(optionalNap.isPresent()){
                        Nap nap = optionalNap.get();
                        Optional<Pos> optionalPos = posRepository.findByCodAndNap(data[3], nap);
                        if(optionalPos.isPresent()){
                            return new ResponseEntity<String>("la posicion ya existe: " + srt, HttpStatus.CONFLICT);    
                        }else{
                            Pos pos = Pos.builder()
                                .cod(data[3])
                                .nap(nap)
                                .estado(true)
                                .build();
                            posRepository.save(pos);
                            return new ResponseEntity<String>("posicion registrada exitosamente", HttpStatus.OK);
                        }
                    }else{
                        Nap nap = Nap.builder()
                            .cod(data[2])
                            .fdt(fdt)
                            .estado(true)
                            .build();
                        napRepository.save(nap);
                        Pos pos = Pos.builder()
                            .cod(data[3])
                            .nap(nap)
                            .estado(true)
                            .build();
                        posRepository.save(pos);
                        return new ResponseEntity<>("se registro Nap y Pos", HttpStatus.OK);
                    }   
                } else {
                    return new ResponseEntity<>("la caja fdt no existe: "+srt, HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<String>("error debe segir ASD-12-12-12",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("error al momento de ingresar los datos: "+ value, HttpStatus.NOT_FOUND);
        }
    }

    private String normalizarPArtes(String data){
        String[] partes = data.split("-");
        for (int i = 0; i < partes.length; i++) {
            
            if (partes[i].matches("[0-9]") && partes[i].length() == 1) {
                partes[i] = "0"+partes[i];
            }
        }
        return String.join("-", partes);
    }

    private String[] getRoutes(String srt){
        String parts[] = srt.split("-");
        if(parts.length == 4){
            return parts;
        }else{
            return null;
        }
    }

    public ResponseEntity<List<Pos>> getAllPossByNap(Long id_nap) {
        try {
            Optional<Nap> optionalNap = napRepository.findById(id_nap);
            List<Pos> poss = posRepository.findAllByNap(optionalNap.get());
            return new ResponseEntity<>(poss, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
