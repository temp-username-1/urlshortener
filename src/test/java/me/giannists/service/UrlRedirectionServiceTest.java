package me.giannists.service;

import me.giannists.persistence.ShortenedUrlEntityDao;
import me.giannists.persistence.model.ShortenedUrlEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UrlRedirectionServiceTest {

    private ShortenedUrlEntity shortenedUrlEntity;

    @Mock
    private ShortenedUrlEntityDao shortenedUrlEntityDao;

    @InjectMocks
    private UrlRedirectionService urlRedirectionService;

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
    public void retrievingExistingUrlShouldReturnUriAndIncreaseCounter() {
        // given
        when(shortenedUrlEntityDao.findByRetrievalKey(anyString())).thenReturn(shortenedUrlEntity);

        // when
        urlRedirectionService.retrieve(shortenedUrlEntity.getRetrievalKey());

        // then
        assertEquals(4, shortenedUrlEntity.getRedirections());
    }

    @Test
    public void retrievingNonExistingUrlShouldThrowException() {
        when(shortenedUrlEntityDao.findByRetrievalKey(anyString())).thenReturn(null);

        assertThatThrownBy(() -> urlRedirectionService.retrieve(shortenedUrlEntity.getRetrievalKey()))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
