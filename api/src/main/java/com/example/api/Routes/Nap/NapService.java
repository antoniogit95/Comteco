package com.example.api.Routes.Nap;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NapService {

    private NapRepository napRepository;

    public List<Nap> getAllNaps() {
        return napRepository.findAll();
    }
    
}
