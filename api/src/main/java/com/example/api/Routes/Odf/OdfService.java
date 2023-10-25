package com.example.api.Routes.Odf;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OdfService{
    private OdfRepository odfRepository;

    public List<Odf> getAllOdfs(){
        return odfRepository.findAll();
    }

    public Optional<Odf> getOdfById(Long id) {
        return odfRepository.findById(id);
        
    }
}