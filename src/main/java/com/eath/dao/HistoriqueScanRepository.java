package com.eath.dao;

import com.eath.entite.HistoriqueScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueScanRepository extends JpaRepository<HistoriqueScan, Integer> {
}
