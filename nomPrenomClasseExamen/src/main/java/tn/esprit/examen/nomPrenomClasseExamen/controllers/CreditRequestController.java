package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.CreditRequest;
import tn.esprit.examen.nomPrenomClasseExamen.services.CreditRequestService;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin
public class CreditRequestController {

    @Autowired
    private CreditRequestService creditService;

    @GetMapping
    public List<CreditRequest> getAll() {
        return creditService.getAll();
    }

    @GetMapping("/{id}")
    public CreditRequest getById(@PathVariable Long id) {
        return creditService.getById(id);
    }

    @PostMapping
    public CreditRequest create(@RequestBody CreditRequest request) {
        return creditService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        creditService.delete(id);
    }

    @GetMapping("/region/{regionName}")
    public List<CreditRequest> getByRegion(@PathVariable String regionName) {
        return creditService.findByRegion(regionName);
    }

    @GetMapping("/user/{userId}")
    public List<CreditRequest> getByUser(@PathVariable Long userId) {
        return creditService.findByUser(userId);
    }
}
