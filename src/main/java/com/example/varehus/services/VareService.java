package com.example.varehus.services;

import com.example.varehus.domain.Vare;
import com.example.varehus.repositories.VareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VareService {

    @Autowired
    private VareRepository vareRepository;

    @Autowired
    private VareHistorikkService vareHistorikkService;

    public List<Vare> list() {
        return vareRepository.findAll();
    }

    public Vare create(Vare vare) {
        return vareRepository.save(vare);
    }

    public void update(Vare vare) {
        vareHistorikkService.lagreEndringer(vare);
    }

    public void delete(Long id){
        vareRepository.deleteById(id);
    }

    public Vare getById(Long id){
        return vareRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
}
