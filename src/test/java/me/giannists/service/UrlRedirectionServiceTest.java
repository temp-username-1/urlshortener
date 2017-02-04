package me.giannists.service;

import me.giannists.persistence.ShortenedUrlEntityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UrlRedirectionServiceTest {

    @Mock
    private ShortenedUrlEntityDao shortenedUrlEntityDao;

    @InjectMocks
    private UrlRedirectionService urlRedirectionService;

    @Test
    public void retrievingExistingUrlShouldReturnUriAndIncreaseCounter() {

    }

}
