package com.example.varehus.controllers;

import com.example.varehus.domain.Vare;
import com.example.varehus.services.VareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vare")
public class VareController {

    @Autowired
    VareService vareService;

    @GetMapping("/{id}")
    public Vare getVare(@PathVariable Long id){
        return vareService.getById(id);
    }

    @GetMapping("/list")
    public List<Vare> vareListe() {
        return vareService.list();
    }

    @PostMapping("/")
    public void leggTil(@RequestBody Vare vare) {
        vareService.create(vare);
    }

    @PutMapping("/")
    public void oppdater(@RequestBody Vare vare) {
        vareService.update(vare);
    }

    @DeleteMapping("/")
    public void slett(@RequestBody Long id){
        vareService.delete(id);
    }
}
