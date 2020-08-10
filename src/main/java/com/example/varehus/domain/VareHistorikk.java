package com.example.varehus.domain;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Random;

@Data
@Entity
@Builder
@AllArgsConstructor
public class VareHistorikk {

    @Id
    @GeneratedValue
    Long id;
    String feltnavn;
    String gammelt;
    String ny;
    LocalDate dato;

    public VareHistorikk(){
        id = new Random().nextLong();
        feltnavn = "";
        gammelt = "";
        ny = "";
        dato = LocalDate.now();
    }
}
