package com.example.api.Routes.Fdt;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.Routes.Odf.Odf;
import com.example.api.Routes.Odf.OdfService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FdtService {

    private FdtRepository fdtRepository;
    private OdfService odfService;

    public List<Fdt> getAllFdts() {
        return fdtRepository.findAll();
    }

    public Optional<Fdt> getFdtById(Long id_fdt){
        return fdtRepository.findById(id_fdt);
    }

    public ResponseEntity<List<Fdt>> getAllFdtsByOdf(Long id_odf) {
        try {
            Optional<Odf> optionalOdf = odfService.getOdfById(id_odf);
            List<Fdt> fdts = fdtRepository.findAllByOdf(optionalOdf.get());
            return new ResponseEntity<>(fdts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public Fdt updateFdt(Long id, Fdt fdt) {
        if(fdtRepository.existsById(id)){
            return fdtRepository.save(fdt);
        }
        return null;
    }

    public boolean deleteFdt(Long id) {
        if (fdtRepository.existsById(id)) {
            fdtRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Fdt createFdt(Long id_odf, Fdt fdt) {
        try {
            return fdtRepository.save(fdt);
        } catch (Exception e) {
            return null;
        }
    }

}
