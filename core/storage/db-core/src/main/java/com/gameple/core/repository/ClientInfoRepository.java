package com.gameple.core.repository;

import com.gameple.core.entity.ClientInfo;
import com.gameple.core.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long> {
    boolean existsByRedirectUrlAndClientType(String redirectUrl, ClientType clientType);
}
