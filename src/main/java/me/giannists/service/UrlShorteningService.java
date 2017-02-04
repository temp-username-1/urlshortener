package me.giannists.service;

import me.giannists.persistence.ShortenedUrlEntityDao;
import me.giannists.persistence.model.ShortenedUrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

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
                .redirections(0)
                .uri(getUriSafe(typedUrl))
                .retrievalKey(keyGenerationService.generateRandomString())
                .build()
        );
    }

    public ShortenedUrlEntity find(String retrievalKey) {
        return Optional.ofNullable(shortenedUrlEntityDao.findByRetrievalKey(retrievalKey))
                .orElseThrow(() -> new EntityNotFoundException("Could not find url with " +
                        "retrieval key : " + retrievalKey));
    }

    private URI getUriSafe(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
