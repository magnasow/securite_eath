package com.eath.Service.Implement;

import com.eath.Service.NutritionService;
import com.eath.entite.Nutrition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NutritionServiceImpl implements NutritionService {

    @Override
    public List<Nutrition> getNutritions() {
        List<Nutrition> nutritions = new ArrayList<>();

        // Définition des titres et descriptions multilingues
        Map<String, String> titre1 = new HashMap<>();
        titre1.put("fr", "Les bienfaits des super-aliments sur la santé");
        titre1.put("en", "The Benefits of Superfoods for Health");
        titre1.put("es", "Los beneficios de los superalimentos para la salud");
        titre1.put("de", "Die Vorteile von Superfoods für die Gesundheit");
        titre1.put("hi", "स्वास्थ्य के लिए सुपरफूड के फायदे");
        titre1.put("ru", "Польза суперфудов для здоровья");

        Map<String, String> description1 = new HashMap<>();
        description1.put("fr", "Les super-aliments sont des ingrédients naturels riches en nutriments essentiels...");
        description1.put("en", "Superfoods are natural ingredients rich in essential nutrients...");
        description1.put("es", "Los superalimentos son ingredientes naturales ricos en nutrientes esenciales...");
        description1.put("de", "Superfoods sind natürliche Zutaten, die reich an essentiellen Nährstoffen sind...");
        description1.put("hi", "सुपरफूड्स प्राकृतिक अवयव हैं जो आवश्यक पोषक तत्वों से भरपूर होते हैं...");
        description1.put("ru", "Суперфуды — это натуральные ингредиенты, богатые необходимыми питательными веществами...");

        nutritions.add(new Nutrition(
                "assets/images/nutrition1.png",
                titre1,
                description1,
                "Fatou Ndiaye",
                "1 octobre 2024"
        ));

        // Ajout d'autres éléments nutritionnels avec titres et descriptions multilingues
        Map<String, String> titre2 = new HashMap<>();
        titre2.put("fr", "L'importance des fibres dans notre alimentation");
        titre2.put("en", "The Importance of Fiber in Our Diet");
        titre2.put("es", "La importancia de las fibras en nuestra alimentación");
        titre2.put("de", "Die Bedeutung von Ballaststoffen in unserer Ernährung");
        titre2.put("hi", "हमारे आहार में फाइबर का महत्व");
        titre2.put("ru", "Важность клетчатки в нашем рационе");

        Map<String, String> description2 = new HashMap<>();
        description2.put("fr", "Les fibres alimentaires jouent un rôle essentiel dans notre santé...");
        description2.put("en", "Dietary fiber plays an essential role in our health...");
        description2.put("es", "Las fibras dietéticas desempeñan un papel esencial en nuestra salud...");
        description2.put("de", "Ballaststoffe spielen eine wesentliche Rolle für unsere Gesundheit...");
        description2.put("hi", "आहार फाइबर हमारे स्वास्थ्य में महत्वपूर्ण भूमिका निभाते हैं...");
        description2.put("ru", "Пищевые волокна играют важную роль в нашем здоровье...");

        nutritions.add(new Nutrition(
                "assets/images/nutrition2.png",
                titre2,
                description2,
                "Mamadou Diop",
                "3 octobre 2024"
        ));

        // Continuez à ajouter les autres objets Nutrition selon le même principe

        return nutritions;
    }
}
