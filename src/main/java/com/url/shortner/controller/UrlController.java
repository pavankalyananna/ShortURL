package com.url.shortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.entity.Url;
import com.url.shortner.service.UrlService;

@RestController
@RequestMapping("/api/urls")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<Url> createUrl(@RequestBody String originalUrl) {
        Url url = urlService.createUrl(originalUrl);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Url> getUrlByShortUrl(@PathVariable String shortUrl) {
        Url url = urlService.getUrlByShortUrl(shortUrl);
        if (url!= null) {
            urlService.createUrl(url.getOriginalUrl()); 
            return ResponseEntity.ok(url);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}