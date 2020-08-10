package com.example.varehus.services;

import com.example.varehus.domain.Vare;
import com.example.varehus.domain.VareHistorikk;
import com.example.varehus.repositories.VareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VareHistorikkService {
    @Autowired
    VareService vareService;

    @Autowired
    VareRepository vareRepository;

    public void lagreEndringer(Vare ny) {
        Vare gammelt = vareService.getById(ny.getId());
        List<VareHistorikk> historikkList = gammelt.getVareHistorikk();

        if (gammelt.getNavn().compareTo(ny.getNavn()) != 0) {
            historikkList.add(VareHistorikk.builder()
                    .feltnavn("navn")
                    .gammelt(gammelt.getNavn())
                    .ny(ny.getNavn())
                    .dato(LocalDate.now())
                    .build());
        }

        if (!gammelt.getAntall().equals(ny.getAntall())) {
            historikkList.add(VareHistorikk.builder()
                    .feltnavn("antall")
                    .gammelt("" + gammelt.getAntall())
                    .ny("" + ny.getAntall())
                    .dato(LocalDate.now())
                    .build());
        }

        if (gammelt.getPris() + 0.01 > ny.getPris() && gammelt.getPris() - 0.01 > ny.getPris() ||
                gammelt.getPris() + 0.01 < ny.getPris() && gammelt.getPris() - 0.01 < ny.getPris()
        ) {
            historikkList.add(VareHistorikk.builder()
                    .feltnavn("pris")
                    .gammelt("" + gammelt.getPris())
                    .ny("" + ny.getPris())
                    .dato(LocalDate.now())
                    .build());
        }

        if (gammelt.getVekt() + 0.01 > ny.getVekt() && gammelt.getVekt() - 0.01 > ny.getVekt() ||
                gammelt.getVekt() + 0.01 < ny.getVekt() && gammelt.getVekt() - 0.01 < ny.getVekt()
        ) {
            historikkList.add(VareHistorikk.builder()
                    .feltnavn("vekt")
                    .gammelt("" + gammelt.getPris())
                    .ny("" + ny.getPris())
                    .dato(LocalDate.now())
                    .build());
        }

        ny.setVareHistorikk(historikkList);
        vareRepository.save(ny);
    }
}
