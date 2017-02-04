package me.giannists.rest.dto;

import lombok.Builder;
import lombok.Data;
import me.giannists.persistence.model.ShortenedUrlEntity;

@Data
@Builder
public class UrlDto {

    String url;

    String retrievalKey;

    int redirections;

    public static UrlDto format(ShortenedUrlEntity shortenedUrlEntity) {
        return UrlDto.builder()
                .url(shortenedUrlEntity.getUri().toString())
                .retrievalKey(shortenedUrlEntity.getRetrievalKey())
                .redirections(shortenedUrlEntity.getRedirections())
                .build();
    }
}
