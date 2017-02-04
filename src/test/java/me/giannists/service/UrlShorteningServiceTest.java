package me.giannists.service;

import me.giannists.persistence.ShortenedUrlEntityDao;
import me.giannists.persistence.model.ShortenedUrlEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UrlShorteningServiceTest {

    private final String VALID_URL = "http://www.google.com/";
    private final String INVALID_URL = "^aa";

    private ShortenedUrlEntity shortenedUrlEntity;

    @Mock
    private ShortenedUrlEntityDao shortenedUrlEntityDao;

    @Spy
    private KeyGenerationService keyGenerationService;

    @InjectMocks
    private UrlShorteningService urlShorteningService;

    @Before
    public void init() {
        shortenedUrlEntity = new ShortenedUrlEntity();

        shortenedUrlEntity.setId(1);
        shortenedUrlEntity.setRetrievalKey("mOckK");
        shortenedUrlEntity.setCreated(new Date());
        shortenedUrlEntity.setRedirections(3);
        shortenedUrlEntity.setUri(URI.create("http://www.google.com/"));
    }

    @Test
    public void urlShortenWithValidUrlShouldCreateEntityWithoutRedirections() {
        // when
        urlShorteningService.shorten(VALID_URL);

        // then
        verify(keyGenerationService, times(1)).generateRandomString();
        verify(shortenedUrlEntityDao, times(1)).save(any(ShortenedUrlEntity.class));
    }

    @Test
    public void urlShortenWithInvalidUrlShouldThrowException() {
        assertThatThrownBy(() -> urlShorteningService.shorten(INVALID_URL))
                .isInstanceOf(IllegalArgumentException.class);
        verify(shortenedUrlEntityDao, times(0)).save(any(ShortenedUrlEntity.class));
    }

    @Test
    public void urlFindWithInvalidRetrievalKeyShouldThrowException() {
        when(shortenedUrlEntityDao.findByRetrievalKey("key")).thenReturn(null);

        assertThatThrownBy(() -> urlShorteningService.find("key"))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
