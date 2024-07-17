package com.url.shortner.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortner.entity.Url;
import com.url.shortner.urlRepository.UrlRepository;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public Url createUrl(String originalUrl) {
    	
        Url url = urlRepository.findByOriginalUrl(originalUrl);
        
        if(url==null) {
        	Url url2=new Url();
        url2.setOriginalUrl(originalUrl);
        url2.setShortUrl(generateShortUrl());
        url2.setClicks(0);
        return urlRepository.save(url2);
        }
        else {
        	url.setClicks(url.getClicks()+1);
        	return urlRepository.save(url);
        }
    }

    public Url getUrlByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    private String generateShortUrl() {
        return "shortUrl-" + UUID.randomUUID().toString().substring(0, 6);
    }
}