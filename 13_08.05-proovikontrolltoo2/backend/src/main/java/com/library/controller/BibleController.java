package com.library.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/bibles")
@CrossOrigin(origins = "http://localhost:3000")
public class BibleController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<String> getBibles() {
        String url = "https://holy-bible-api.com/bibles";
        String result = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(result);
    }
}