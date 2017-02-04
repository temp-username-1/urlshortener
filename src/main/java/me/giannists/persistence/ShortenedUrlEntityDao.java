package me.giannists.persistence;

import me.giannists.persistence.model.ShortenedUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ShortenedUrlEntityDao extends JpaRepository<ShortenedUrlEntity, BigInteger> {

    ShortenedUrlEntity findByRetrievalKey(String retrievalKey);

}
