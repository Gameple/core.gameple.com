package com.gameple.core.repository;

import com.gameple.core.entity.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long> {
    boolean existsByRedirectUrl(String redirectUrl);
}
