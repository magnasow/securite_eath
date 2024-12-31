package com.eath.Service.Implement;

import com.eath.entite.Actuality;
import com.eath.Service.ActualityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActualityServiceImpl implements ActualityService {

    @Override
    public List<Actuality> getActualites() {
        List<Actuality> actualites = new ArrayList<>();

        actualites.add(createActuality(
                "assets/images/actualite1.png",
                createTitles("Les tendances culinaires de 2024", "Culinary Trends of 2024", "Kulinarische Trends 2024",
                        "Tendencias culinarias de 2024", "2024 की पाक प्रवृत्तियाँ", "Кулинарные тренды 2024"),
                createDescriptions("Les tendances culinaires pour 2024 montrent un virage vers une alimentation durable et consciente...",
                        "The culinary trends for 2024 show a shift towards sustainable and mindful eating...",
                        "Die kulinarischen Trends für 2024 zeigen eine Wende hin zu nachhaltigem und bewusstem Essen...",
                        "Las tendencias culinarias de 2024 muestran un cambio hacia una alimentación sostenible y consciente...",
                        "2024 के लिए पाक प्रवृत्तियाँ स्थायी और विचारशील खाने की ओर बदलाव दिखाती हैं...",
                        "Кулинарные тренды 2024 года показывают переход к устойчивому и осознанному питанию..."),
                "Amina Sow",
                "2 octobre 2024"
        ));

        actualites.add(createActuality(
                "assets/images/actualite2.png",
                createTitles("L'impact de la pandémie sur la restauration", "The Impact of the Pandemic on Restaurants",
                        "Die Auswirkungen der Pandemie auf Restaurants", "El impacto de la pandemia en los restaurantes",
                        "रेस्टोरेंट पर महामारी का प्रभाव", "Влияние пандемии на рестораны"),
                createDescriptions("La pandémie a transformé l'industrie de la restauration. Face aux restrictions sanitaires...",
                        "The pandemic has transformed the restaurant industry. Faced with health restrictions...",
                        "Die Pandemie hat die Gastronomiebranche verändert. Angesichts der Gesundheitsauflagen...",
                        "La pandemia ha transformado la industria de los restaurantes. Frente a las restricciones sanitarias...",
                        "महामारी ने रेस्तरां उद्योग को बदल दिया है। स्वास्थ्य प्रतिबंधों का सामना करना पड़ा...",
                        "Пандемия изменила ресторанную индустрию. Столкнувшись с санитарными ограничениями..."),
                "Serigne Sène",
                "5 octobre 2024"
        ));

        // Ajoutez d'autres actualités ici...

        return actualites;
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

    private Actuality createActuality(String image, Map<String, String> titres, Map<String, String> descriptions, String auteur, String date) {
        return new Actuality(image, titres, descriptions, auteur, date);
    }
}
