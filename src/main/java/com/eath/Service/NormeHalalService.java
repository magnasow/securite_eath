package com.eath.Service;


import com.eath.entite.NormeHalal;

import java.util.List;

public interface NormeHalalService {
    NormeHalal addNormeHalal(NormeHalal normeHalal);
    List<NormeHalal> getAllNormeHalal();
    NormeHalal getNormeHalalById(Integer idNormeHalal);
    NormeHalal updateNormeHalal(Integer idNormeHalal, NormeHalal normeHalal);
    void deleteNormeHalal(Integer idNormeHalal);
}