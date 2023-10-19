package com.example.api.OdfFtp.Odf;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OdfService{
    private OdfRepository odfRepository;

    public List<Odf> getAllOdfs(){
        return odfRepository.findAll();
    }
}