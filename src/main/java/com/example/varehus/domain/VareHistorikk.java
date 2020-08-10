package com.example.varehus.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class VareHistorikk {

    @Id
    @GeneratedValue
    Long id;
    String feltnavn;
    String gammelt;
    String ny;
    LocalDate dato;
}
