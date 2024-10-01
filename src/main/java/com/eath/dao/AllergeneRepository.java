package com.eath.dao;

import com.eath.entite.Allergene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergeneRepository extends JpaRepository<Allergene, Integer> {
}
