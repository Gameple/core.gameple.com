package com.gameple.core.entity;

import com.gameple.core.helper.IpAddressHolder;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class BaseEntityListener {

    @PrePersist
    public void prePersist(BaseEntity entity) {
        if (entity.getCreatedIpAddress() == null) {
            entity.setCreatedIpAddress(IpAddressHolder.get());
        }
        entity.setUpdatedIpAddress(IpAddressHolder.get());
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setUpdatedIpAddress(IpAddressHolder.get());
    }
}
