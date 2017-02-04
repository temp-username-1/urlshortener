package me.giannists.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class KeyGenerationService {

    private static final int DEFAULT_MIN_RETRIEVAL_KEY_LENGTH = 5;
    private static final int DEFAULT_MAX_RETRIEVAL_KEY_LENGTH = 6;

    public String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(
               DEFAULT_MIN_RETRIEVAL_KEY_LENGTH,
               DEFAULT_MAX_RETRIEVAL_KEY_LENGTH
        );
    }
}
