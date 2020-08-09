package com.example.varehus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Random;

@Entity
@Value
@Builder
@AllArgsConstructor
@Data
public class Vare {
    @Id
    @GeneratedValue
    Long id;
    String navn;
    Double vekt;
    Double pris;
    Integer antall;

    public Vare() {
        id = new Random().nextLong();
        navn = "";
        vekt = 0.0;
        pris = 0.0;
        antall = 0;
    }
}
