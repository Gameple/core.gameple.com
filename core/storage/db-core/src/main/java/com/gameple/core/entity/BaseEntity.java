package com.gameple.core.entity;

import com.gameple.core.enums.EntityStatus;
import com.gameple.core.helper.AesEncryptConverter;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
@Getter
@EntityListeners(BaseEntityListener.class)
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private EntityStatus status = EntityStatus.ACTIVE;

    @Column(nullable = false, length = 45)
    @Convert(converter = AesEncryptConverter.class)
    private String createdIpAddress;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;


    @Column(nullable = false, length = 45)
    @Convert(converter = AesEncryptConverter.class)
    private String updatedIpAddress;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public void active() {
        this.status = EntityStatus.ACTIVE;
    }

    public boolean isActive() {
        return this.status == EntityStatus.ACTIVE;
    }

    public void delete() {
        this.status = EntityStatus.DELETED;
    }

    public boolean isDeleted() {
        return this.status == EntityStatus.DELETED;
    }

    public void setCreatedIpAddress(String ip) {
        this.createdIpAddress = ip;
    }

    public void setUpdatedIpAddress(String ip) {
        this.updatedIpAddress = ip;
    }
}
