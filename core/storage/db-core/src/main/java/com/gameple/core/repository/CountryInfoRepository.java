package com.gameple.core.repository;

import com.gameple.core.entity.CountryInfo;
import com.gameple.core.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryInfoRepository extends JpaRepository<CountryInfo, Long> {

    List<CountryInfo> findAllByStatusOrderBySortOrderDesc(EntityStatus status);

    Optional<CountryInfo> findById(Long id);
}
