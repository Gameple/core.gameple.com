package com.gameple.core.repository;

import com.gameple.core.entity.OAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthTokenRepository extends JpaRepository<OAuthToken, Long> {

}
