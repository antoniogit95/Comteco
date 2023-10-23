package com.example.api.Routes.Fdt;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FdtService {

    private FdtRepository ftpRepository;

    public List<Fdt> getAllFdts() {
        return ftpRepository.findAll();
    }

}
