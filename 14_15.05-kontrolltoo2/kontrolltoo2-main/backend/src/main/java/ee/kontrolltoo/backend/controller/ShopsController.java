package ee.kontrolltoo.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class ShopsController {

    @GetMapping("shops")
    public Object getShops() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                "https://marineregions.org/rest/getGazetteerTypes.json",
                Object.class
        );
    }
}