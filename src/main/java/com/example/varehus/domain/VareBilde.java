package com.example.varehus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.File;
import java.util.Random;

@Data
@Entity
@Builder
@AllArgsConstructor
public class VareBilde {
    @Id
    @GeneratedValue
    Long id;
    @Lob
    byte[] bilde;

    public VareBilde(){
        id = new Random().nextLong();
    }
}
