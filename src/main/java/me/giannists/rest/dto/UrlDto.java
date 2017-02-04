package me.giannists.rest.dto;

import lombok.Data;
import me.giannists.persistence.model.ShortenedUrlEntity;

@Data
public class UrlDto {

    String url;

    String retrievalKey;

    int clicks;

    public static UrlDto format(ShortenedUrlEntity shortenedUrlEntity) {
        UrlDto dto = new UrlDto();
        dto.setUrl(shortenedUrlEntity.getUri().toString());
        dto.setRetrievalKey(shortenedUrlEntity.getRetrievalKey());
        dto.setClicks(shortenedUrlEntity.getClicks());

        return dto;
    }
}
