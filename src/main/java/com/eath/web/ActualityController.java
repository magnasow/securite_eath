package com.eath.web;
import com.eath.Service.ActualityService;
import com.eath.entite.Actuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/actualites")
public class ActualityController {
    @Autowired
    private ActualityService actualityService;

    @GetMapping
    public List<Actuality> getActualites() {
        return actualityService.getActualites();
    }
}
