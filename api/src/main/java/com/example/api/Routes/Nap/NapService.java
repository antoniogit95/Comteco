package com.example.api.Routes.Nap;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.Routes.Fdt.Fdt;
import com.example.api.Routes.Fdt.FdtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NapService {

    private NapRepository napRepository;
    private FdtService fdtService;

    public List<Nap> getAllNaps() {
        return napRepository.findAll();
    }

    public Optional<Nap> getNapById(Long id){
        return napRepository.findById(id);
    }

    public ResponseEntity<List<Nap>> getAllNapsByOdf(Long id_fdt) {
        try {
            Optional<Fdt> optionalFdt = fdtService.getFdtById(id_fdt);
            List<Nap> naps = napRepository.findAllByFdt(optionalFdt.get());
            return new ResponseEntity<>(naps, HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
}
