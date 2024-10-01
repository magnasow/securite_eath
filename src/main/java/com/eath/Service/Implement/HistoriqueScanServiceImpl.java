package com.eath.Service.Implement;

import com.eath.Service.HistoriqueScanService;
import com.eath.dao.HistoriqueScanRepository;
import com.eath.entite.HistoriqueScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueScanServiceImpl implements HistoriqueScanService {

    @Autowired
    private HistoriqueScanRepository historiqueScanRepository;

    @Override
    public List<HistoriqueScan> getAllHistoriqueScans() {
        return historiqueScanRepository.findAll();
    }

    @Override
    public Optional<HistoriqueScan> getHistoriqueScanById(Integer id) {
        return historiqueScanRepository.findById(id);
    }

    @Override
    public HistoriqueScan createHistoriqueScan(HistoriqueScan historiqueScan) {
        return historiqueScanRepository.save(historiqueScan);
    }

    @Override
    public Optional<HistoriqueScan> updateHistoriqueScan(Integer id, HistoriqueScan historiqueScanDetails) {
        Optional<HistoriqueScan> historiqueScan = historiqueScanRepository.findById(id);
        if (historiqueScan.isPresent()) {
            HistoriqueScan hs = historiqueScan.get();
            hs.setUtilisateur(historiqueScanDetails.getUtilisateur());
            hs.setProduit(historiqueScanDetails.getProduit());
            hs.setDateScan(historiqueScanDetails.getDateScan());
            return Optional.of(historiqueScanRepository.save(hs));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteHistoriqueScan(Integer id) {
        if (historiqueScanRepository.existsById(id)) {
            historiqueScanRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
