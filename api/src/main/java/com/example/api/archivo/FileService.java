package com.example.api.archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public List<File> getFilesAll(){
        return fileRepository.findAll();
    }

    public ResponseEntity<String> saveFile(@RequestParam("archive") MultipartFile archivo){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream()));
            String linea;
            boolean firstLine = true;
            while ((linea = br.readLine()) != null) {
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String[] partes = linea.split(";"); 
                System.out.println("---------------------"+partes[0]+"---------------");
                if (partes.length == 23) { 
                    File nuevoArchivo = new File();
                    nuevoArchivo.setFECHA_CREACION(getFecha(partes[0].trim()));
                    nuevoArchivo.setCOD_TIPO_SOL(Integer.parseInt(partes[1].trim()));
                    nuevoArchivo.setTIPO_SOLICITUD(partes[2].trim());
                    nuevoArchivo.setCOD_TIPO(Integer.parseInt(partes[3].trim()));
                    nuevoArchivo.setTIPO_TRABAJO(partes[4].trim());
                    nuevoArchivo.setCOD_PLAN_COMERCIAL(Integer.parseInt(partes[5].trim()));
                    nuevoArchivo.setPLAN_COMERCIAL(partes[6].trim());
                    nuevoArchivo.setCOMPONENTE(partes[7].trim());
                    nuevoArchivo.setCLASE_SERVICIO(partes[8].trim());
                    nuevoArchivo.setAREA_SERVICIO(partes[9].trim());
                    nuevoArchivo.setUBICACION(partes[10].trim());
                    nuevoArchivo.setPRODUCTO(partes[11].trim());
                    nuevoArchivo.setORDEN(partes[12].trim());
                    nuevoArchivo.setDESC_SERVICIO(partes[13].trim());
                    nuevoArchivo.setNUMERO_SERVICIO(partes[14].trim());
                    nuevoArchivo.setESTADO_COMPONENTE(partes[15].trim());
                    nuevoArchivo.setNAP(partes[16].trim());
                    nuevoArchivo.setPOSICION(Integer.parseInt(partes[17].trim()));
                    nuevoArchivo.setDESCRIPCION(partes[18].trim());
                    nuevoArchivo.setCLIENTE(partes[19].trim());
                    nuevoArchivo.setDIRECCION(partes[20].trim());
                    nuevoArchivo.setCOD_ESTADO_OT(partes[21].trim());
                    nuevoArchivo.setUNIDAD_OPERATIVA(partes[22].trim());
                    fileRepository.save(nuevoArchivo);
                }
            }

            br.close();
            return ResponseEntity.ok("Archivo cargado y guardado en la base de datos.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Date getFecha(String fechaString){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date utilDate = null;
        try {
            utilDate = formatoFecha.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        if (utilDate != null) {
            return new Date(utilDate.getTime());
        } else {
            return null;
        }
    }
}
