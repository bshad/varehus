package com.example.varehus.repositories;

import com.example.varehus.domain.Vare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VareRepository extends JpaRepository<Vare, Long> {

}
