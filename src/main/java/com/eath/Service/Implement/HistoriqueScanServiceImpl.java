package com.eath.Service.Implement;

import com.eath.entite.HistoriqueScan;
import com.eath.dao.HistoriqueScanRepository;
import com.eath.Service.HistoriqueScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueScanServiceImpl implements HistoriqueScanService {

    @Autowired
    private HistoriqueScanRepository historiqueScanRepository;

    @Override
    public HistoriqueScan enregistrerScan(HistoriqueScan historiqueScan) {
        // Enregistrer le scan dans la base de données
        return historiqueScanRepository.save(historiqueScan);
    }
}
