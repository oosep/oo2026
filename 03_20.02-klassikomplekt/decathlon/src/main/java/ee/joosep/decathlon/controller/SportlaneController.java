package ee.joosep.decathlon.controller;

import ee.joosep.decathlon.entity.Sportlane;
import ee.joosep.decathlon.entity.Tulemus;
import ee.joosep.decathlon.repository.SportlaneRepository;
import ee.joosep.decathlon.repository.TulemusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sportlased")
public class SportlaneController {

    @Autowired
    private SportlaneRepository sportlaneRepository;

    @Autowired
    private TulemusRepository tulemusRepository;

    @GetMapping
    public List<Sportlane> getSportlased() {
        return sportlaneRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> lisaSportlane(@RequestBody Sportlane sportlane) {
        if (sportlane.getNimi() == null || sportlane.getNimi().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Sportlase nimi ei tohi olla tühi!");
        }
        if (sportlane.getRiik() == null || sportlane.getRiik().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Sportlase riik ei tohi olla tühi!");
        }
        sportlaneRepository.save(sportlane);
        return ResponseEntity.ok(sportlaneRepository.findAll());
    }

    @PostMapping("/{id}/tulemus")
    public ResponseEntity<?> lisaTulemus(@PathVariable Long id, @RequestBody Tulemus tulemus) {
        Optional<Sportlane> sportlaneOpt = sportlaneRepository.findById(id);
        if (sportlaneOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Sportlast selle ID-ga ei leitud!");
        }
        if (tulemus.getSpordiala() == null || tulemus.getSpordiala().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Spordiala ei tohi olla tühi!");
        }
        if (tulemus.getVaartus() < 0) {
            return ResponseEntity.badRequest().body("Viga: Tulemus ei tohi olla negatiivne!");
        }

        double punktid = tulemus.getVaartus() * 10;
        tulemus.setPunktid(punktid);
        tulemus.setSportlane(sportlaneOpt.get());
        tulemusRepository.save(tulemus);
        return ResponseEntity.ok(tulemusRepository.findBySportlaneId(id));
    }

    @GetMapping("/{id}/punktid")
    public ResponseEntity<?> getKogupunktid(@PathVariable Long id) {
        List<Tulemus> tulemused = tulemusRepository.findBySportlaneId(id);
        double kogupunktid = tulemused.stream()
                .mapToDouble(Tulemus::getPunktid)
                .sum();
        return ResponseEntity.ok("Kogupunktid: " + kogupunktid);
    }
}