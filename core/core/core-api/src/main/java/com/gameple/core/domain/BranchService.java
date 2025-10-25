package com.gameple.core.domain;

import com.gameple.core.api.controller.v1.response.CountryInfoResponse;
import com.gameple.core.entity.CountryInfo;
import com.gameple.core.enums.EntityStatus;
import com.gameple.core.repository.CountryInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final CountryInfoRepository countryInfoRepository;

    public List<CountryInfoResponse> findCountries() {
        List<CountryInfo> countryInfos = countryInfoRepository.findAllByStatusOrderBySortOrderDesc(EntityStatus.ACTIVE);

        return countryInfos.stream()
                .map(country -> CountryInfoResponse.builder()
                        .id(country.getId())
                        .iso2(country.getIso2())
                        .iso3(country.getIso3())
                        .name(country.getName())
                        .nativeName(country.getNativeName())
                        .phoneCode(country.getPhoneCode())
                        .locale(country.getLocale())
                        .sortOrder(country.getSortOrder())
                        .build())
                .toList();
    }
}
