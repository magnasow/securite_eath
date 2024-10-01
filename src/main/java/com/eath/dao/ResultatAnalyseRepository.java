package com.eath.dao;

import com.eath.entite.ResultatAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatAnalyseRepository extends JpaRepository<ResultatAnalyse, Integer> {
}
