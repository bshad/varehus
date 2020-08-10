package com.example.varehus.domain;

import com.example.varehus.VarehusApplication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Data
public class Vare {
    @Id
    @GeneratedValue
    Long id;
    String navn;
    Double vekt;
    Double pris;
    Integer antall;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    List<VareHistorikk> vareHistorikk;

    public Vare() {
        id = new Random().nextLong();
        navn = "";
        vekt = 0.0;
        pris = 0.0;
        antall = 0;
    }
}
