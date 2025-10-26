package com.gameple.core.entity;

import com.gameple.core.enums.ClientType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "gameple_client_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientInfo extends BaseEntity {

    @Column(length = 50, nullable = false, unique = true)
    private String clientSecret;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private ClientType clientType;

    @Column(length = 50)
    private String redirectUrl;
}
