package com.example.varehus.controllers;

import com.example.varehus.domain.Vare;
import com.example.varehus.domain.VareBilde;
import com.example.varehus.services.VareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/varebilder")
public class VareBilderController {

    @Autowired
    VareService vareService;

    @PostMapping("/{id}")
    public void leggTilBilde(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file ) throws IOException {
        vareService.leggTilBilde(id, file.getBytes());
    }

    @GetMapping("/{id}")
    public List<VareBilde> hentBilder(@PathVariable Long id) {
        Vare vare = vareService.getById(id);
        return vare.getVareBilder();
    }

    @DeleteMapping("/{vareid}/{bildeid}")
    public void slett(@PathVariable Long vareid, @PathVariable Long bildeid){
        vareService.slettBilde(vareid, bildeid);
    }
}
