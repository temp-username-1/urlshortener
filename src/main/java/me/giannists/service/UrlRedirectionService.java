package me.giannists.service;

import me.giannists.persistence.ShortenedUrlEntityDao;
import me.giannists.persistence.model.ShortenedUrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Optional;

@Service
public class UrlRedirectionService {
    private ShortenedUrlEntityDao shortenedUrlEntityDao;

    @Autowired
    public UrlRedirectionService(ShortenedUrlEntityDao shortenedUrlEntityDao){
        this.shortenedUrlEntityDao = shortenedUrlEntityDao;
    }

    public URI retrieve(String retrievalKey) {
        ShortenedUrlEntity shortenedUrlEntity = Optional.ofNullable(
                shortenedUrlEntityDao.findByRetrievalKey(retrievalKey)
        ).orElseThrow(() -> new EntityNotFoundException("Could not find uri with key : " + retrievalKey));

        shortenedUrlEntity.setClicks(shortenedUrlEntity.getClicks() + 1);
        shortenedUrlEntityDao.save(shortenedUrlEntity);

        return shortenedUrlEntity.getUri();

    }
}
