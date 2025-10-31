package com.gameple.core.repository;

import com.gameple.core.entity.OAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthTokenRepository extends JpaRepository<OAuthToken, Long> {

    Optional<OAuthToken> findByCallback(String callback);
}
