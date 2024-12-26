package com.eath.Service.Implement;


import com.eath.entite.Actuality;
import com.eath.Service.ActualityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActualityServiceImpl implements ActualityService {

    @Override
    public List<Actuality> getActualites() {
        List<Actuality> actualites = new ArrayList<>();

        actualites.add(new Actuality("assets/images/actualite1.png", "Les tendances culinaires de 2024",
                "Les tendances culinaires pour 2024 montrent un virage vers une alimentation durable et consciente...",
                "Amina Sow", "2 octobre 2024"));

        actualites.add(new Actuality("assets/images/actualite2.png", "L'impact de la pandémie sur la restauration",
                "La pandémie a transformé l'industrie de la restauration. Face aux restrictions sanitaires...",
                "Serigne Sène", "5 octobre 2024"));

        actualites.add(new Actuality("assets/images/actualite3.png", "La digitalisation du secteur alimentaire",
                "La digitalisation transforme profondément le secteur alimentaire, des restaurants aux supermarchés...",
                "Mariama Diagne", "3 octobre 2024"));

        actualites.add(new Actuality("assets/images/actualite4.png", "L'essor des cuisines fantômes",
                "Les cuisines fantômes, ou dark kitchens, sont devenues une tendance majeure dans le secteur de la restauration...",
                "Oumar Ndiaye", "6 octobre 2024"));

        actualites.add(new Actuality("assets/images/actualite5.png", "La montée des alternatives à base de plantes",
                "L'année 2024 voit une explosion des alternatives alimentaires à base de plantes. Les consommateurs...",
                "Moussa Sarr", "4 octobre 2024"));

        return actualites;
    }
}
