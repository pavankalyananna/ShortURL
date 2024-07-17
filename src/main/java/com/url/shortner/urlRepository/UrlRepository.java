package com.url.shortner.urlRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.shortner.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortUrl(String shortUrl);
    Url findByOriginalUrl(String originalUrl);
}