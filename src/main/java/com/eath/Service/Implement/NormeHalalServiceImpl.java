package com.eath.Service.Implement;

import com.eath.Service.NormeHalalService;
import com.eath.dao.NormeHalalRepository;
import com.eath.entite.NormeHalal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NormeHalalServiceImpl implements NormeHalalService {

    private final NormeHalalRepository normeHalalRepository;

    @Override
    public NormeHalal addNormeHalal(NormeHalal normeHalal) {
        return normeHalalRepository.save(normeHalal);
    }

    @Override
    public List<NormeHalal> getAllNormeHalal() {
        return normeHalalRepository.findAll();
    }

    @Override
    public NormeHalal getNormeHalalById(Integer idNormeHalal) {
        return normeHalalRepository.findById(idNormeHalal)
                .orElseThrow(() -> new RuntimeException("NormeHalal not found"));
    }

    @Override
    public NormeHalal updateNormeHalal(Integer idNormeHalal, NormeHalal normeHalal) {
        NormeHalal existingNormeHalal = getNormeHalalById(idNormeHalal);
        existingNormeHalal.setDescriptionNormeHalal(normeHalal.getDescriptionNormeHalal());
        existingNormeHalal.setEstHalal(normeHalal.getEstHalal());
        return normeHalalRepository.save(existingNormeHalal);
    }

    @Override
    public void deleteNormeHalal(Integer idNormeHalal) {
        normeHalalRepository.deleteById(idNormeHalal);
    }
}
