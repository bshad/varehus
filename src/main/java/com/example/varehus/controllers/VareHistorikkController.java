package com.example.varehus.controllers;

import com.example.varehus.domain.Vare;
import com.example.varehus.domain.VareHistorikk;
import com.example.varehus.services.VareHistorikkService;
import com.example.varehus.services.VareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/varehistorikk")
public class VareHistorikkController {
    @Autowired
    VareService vareService;

    @Autowired
    VareHistorikkService vareHistorikkService;

    @GetMapping("/")
    public List<VareHistorikk> getVareHistorikk(@RequestParam Long vareId){
        Vare vare = vareService.getById(vareId);
        return vare.getVareHistorikk();
    }
}
