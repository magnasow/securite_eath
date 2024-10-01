package com.eath.Service;

import com.eath.entite.HistoriqueScan;
import java.util.List;
import java.util.Optional;

public interface HistoriqueScanService {
    List<HistoriqueScan> getAllHistoriqueScans();
    Optional<HistoriqueScan> getHistoriqueScanById(Integer id);
    HistoriqueScan createHistoriqueScan(HistoriqueScan historiqueScan);
    Optional<HistoriqueScan> updateHistoriqueScan(Integer id, HistoriqueScan historiqueScanDetails);
    boolean deleteHistoriqueScan(Integer id);
}
