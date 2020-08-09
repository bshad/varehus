package com.example.varehus.services;

import com.example.varehus.domain.Vare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class VareServiceTest {

    @Autowired
    VareService vareService;

    @Test
    public void testFetchGenericRecords(){
        List<Vare> result = vareService.list();
        Assertions.assertEquals(result.size(), 3);
    }
}
