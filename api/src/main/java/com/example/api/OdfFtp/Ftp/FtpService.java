package com.example.api.OdfFtp.Ftp;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FtpService {

    private FtpRepository ftpRepository;

    public List<Ftp> getAllFtps() {
        return ftpRepository.findAll();
    }
    
}
