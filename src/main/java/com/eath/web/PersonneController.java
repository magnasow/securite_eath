package com.eath.web;

import com.eath.dao.PersonneRepository;
import com.eath.entite.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneRepository personneRepository;

    @Autowired
    public PersonneController(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Integer id) {
        Optional<Personne> personne = personneRepository.findById(id);
        return personne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Personne createPersonne(@RequestBody Personne personne) {
        return personneRepository.save(personne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Integer id, @RequestBody Personne personneDetails) {
        Optional<Personne> personne = personneRepository.findById(id);
        if (personne.isPresent()) {
            Personne p = personne.get();
            p.setNomPersonne(personneDetails.getNomPersonne());
            p.setPrenomPersonne(personneDetails.getPrenomPersonne());
            p.setMotDePasse(personneDetails.getMotDePasse());
            p.setEmail(personneDetails.getEmail());
            p.setPhotoDeProfil(personneDetails.getPhotoDeProfil());
            p.setTaille(personneDetails.getTaille());
            p.setPoids(personneDetails.getPoids());
            p.setAge(personneDetails.getAge());
            p.setSexe(personneDetails.getSexe());
            p.setConditionsMedicales(personneDetails.getConditionsMedicales());
            return ResponseEntity.ok(personneRepository.save(p));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Integer id) {
        if (personneRepository.existsById(id)) {
            personneRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
