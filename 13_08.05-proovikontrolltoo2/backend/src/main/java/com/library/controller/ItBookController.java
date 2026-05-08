package com.library.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/itbooks")
@CrossOrigin(origins = "http://localhost:3000")
public class ItBookController {

    @Value("${itbook.api.url}")
    private String itbookApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // URL: GET /api/itbooks/search/react?page=1
    @GetMapping("/search/{query}")
    public ResponseEntity<String> searchBooks(
            @PathVariable String query,
            @RequestParam(defaultValue = "1") int page) {
        String url = itbookApiUrl + "/search/" + query + "/" + page;
        String result = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(result);
    }
}