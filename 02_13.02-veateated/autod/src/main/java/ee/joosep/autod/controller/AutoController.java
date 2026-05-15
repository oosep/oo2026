package ee.joosep.autod.controller;

import ee.joosep.autod.entity.Auto;
import ee.joosep.autod.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autod")
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping
    public List<Auto> getAutod() {
        return autoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> lisaAuto(@RequestBody Auto auto) {

        if (auto.getMark() == null || auto.getMark().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Auto mark ei tohi olla tühi!");
        }

        if (auto.getMudel() == null || auto.getMudel().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Auto mudel ei tohi olla tühi!");
        }

        if (auto.getAasta() < 1886) {
            return ResponseEntity.badRequest().body("Viga: Auto aasta ei saa olla väiksem kui 1886!");
        }

        if (auto.getAasta() > 2026) {
            return ResponseEntity.badRequest().body("Viga: Auto aasta ei saa olla tulevikus!");
        }

        if (auto.getHind() < 0) {
            return ResponseEntity.badRequest().body("Viga: Auto hind ei tohi olla negatiivne!");
        }

        autoRepository.save(auto);
        return ResponseEntity.ok(autoRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public List<Auto> kustutaAuto(@PathVariable Long id) {
        autoRepository.deleteById(id);
        return autoRepository.findAll();
    }
}