package me.giannists.service;

import me.giannists.persistence.ShortenedUrlEntityDao;
import me.giannists.persistence.model.ShortenedUrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UrlShorteningService {

    private ShortenedUrlEntityDao shortenedUrlEntityDao;

    private KeyGenerationService keyGenerationService;

    @Autowired
    public UrlShorteningService(ShortenedUrlEntityDao shortenedUrlEntityDao,
                                KeyGenerationService keyGenerationService) {
        this.shortenedUrlEntityDao = shortenedUrlEntityDao;
        this.keyGenerationService = keyGenerationService;
    }

    public ShortenedUrlEntity shorten(String typedUrl) {
        return shortenedUrlEntityDao.save(ShortenedUrlEntity.builder()
                .clicks(0)
                .uri(getUriSafe(typedUrl))
                .retrievalKey(keyGenerationService.generateRandomString())
                .build()
        );
    }

    private URI getUriSafe(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
