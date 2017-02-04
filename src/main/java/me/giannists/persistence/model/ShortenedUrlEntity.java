package me.giannists.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigInteger;
import java.net.URI;
import java.time.Instant;
import java.util.Date;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShortenedUrlEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    BigInteger id;

    @CreatedDate
    @Column(name = "created", updatable = false)
    private Date created;

    String retrievalKey;

    URI uri;

    int clicks;

    @PrePersist
    public void onCreate() {
        this.created = Date.from(Instant.now());
    }
}
