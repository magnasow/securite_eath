package com.eath.Service.Implement;

import com.eath.entite.Recette;
import com.eath.Service.RecetteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecetteServiceImpl implements RecetteService {

    @Override
    public List<Recette> getRecettes() {
        List<Recette> recettes = new ArrayList<>();

        recettes.add(createRecette(
                "assets/images/recette1.png",
                createTitles("Recette du Poulet Yassa", "Chicken Yassa Recipe", "Hähnchen Yassa Rezept",
                        "Receta de Pollo Yassa", "चिकन यासा रेसिपी", "Рецепт цыпленка Ясса"),
                createDescriptions(
                        "Le poulet Yassa est un plat emblématique de la cuisine sénégalaise...",
                        "Chicken Yassa is a signature dish of Senegalese cuisine...",
                        "Hähnchen Yassa ist ein typisches Gericht der senegalesischen Küche...",
                        "El pollo Yassa es un plato característico de la cocina senegalesa...",
                        "चिकन यासा सेनेगल के व्यंजनों का एक प्रमुख व्यंजन है...",
                        "Цыпленок Ясса — знаковое блюдо сенегальской кухни..."),
                "Chef Ndiaye",
                "28 septembre 2024"
        ));

        recettes.add(createRecette(
                "assets/images/recette2.png",
                createTitles("Recette du Thiéboudienne", "Thiéboudienne Recipe", "Thiéboudienne Rezept",
                        "Receta de Thiéboudienne", "थिएबौडियन रेसिपी", "Рецепт Тьебудьен"),
                createDescriptions(
                        "Le Thiéboudienne, également connu sous le nom de Ceebu Jën...",
                        "Thiéboudienne, also known as Ceebu Jën...",
                        "Thiéboudienne, auch bekannt als Ceebu Jën...",
                        "El Thiéboudienne, también conocido como Ceebu Jën...",
                        "थिएबौडियन, जिसे सीबू जेन के रूप में भी जाना जाता है...",
                        "Тьебудьен, также известный как Ceebu Jën..."),
                "Amina Sow",
                "1 octobre 2024"
        ));

        return recettes;
    }

    @Override
    public List<Recette> getRecettesByLanguage(String lang) {
        List<Recette> recettes = getRecettes();

        return recettes.stream()
                .map(recette -> {
                    // Création d'un titre et description pour la langue spécifiée
                    Map<String, String> titreLang = new HashMap<>();
                    titreLang.put(lang, recette.getTitre().getOrDefault(lang, recette.getTitre().get("fr")));

                    Map<String, String> descriptionLang = new HashMap<>();
                    descriptionLang.put(lang, recette.getDescription().getOrDefault(lang, recette.getDescription().get("fr")));

                    // Retourner une nouvelle Recette avec les titres et descriptions adaptés à la langue
                    Recette recetteLangue = new Recette();
                    recetteLangue.setImage(recette.getImage());
                    recetteLangue.setTitre(titreLang);
                    recetteLangue.setDescription(descriptionLang);
                    recetteLangue.setAuteur(recette.getAuteur());
                    recetteLangue.setDate(recette.getDate());
                    return recetteLangue;
                })
                .collect(Collectors.toList());
    }

    private Map<String, String> createTitles(String fr, String en, String de, String es, String hi, String ru) {
        Map<String, String> titles = new HashMap<>();
        titles.put("fr", fr);
        titles.put("en", en);
        titles.put("de", de);
        titles.put("es", es);
        titles.put("hi", hi);
        titles.put("ru", ru);
        return titles;
    }

    private Map<String, String> createDescriptions(String fr, String en, String de, String es, String hi, String ru) {
        Map<String, String> descriptions = new HashMap<>();
        descriptions.put("fr", fr);
        descriptions.put("en", en);
        descriptions.put("de", de);
        descriptions.put("es", es);
        descriptions.put("hi", hi);
        descriptions.put("ru", ru);
        return descriptions;
    }

    private Recette createRecette(String image, Map<String, String> titres, Map<String, String> descriptions, String auteur, String date) {
        Recette recette = new Recette();
        recette.setImage(image);
        recette.setTitre(titres);
        recette.setDescription(descriptions);
        recette.setAuteur(auteur);
        recette.setDate(date);
        return recette;
    }
}
