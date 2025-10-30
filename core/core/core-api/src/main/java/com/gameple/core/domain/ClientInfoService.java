package com.gameple.core.domain;

import com.gameple.core.enums.ClientType;
import com.gameple.core.helper.error.CoreException;
import com.gameple.core.helper.error.ErrorType;
import com.gameple.core.repository.ClientInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientInfoService {

    private final ClientInfoRepository clientInfoRepository;

    public void validateRedirectUrl(String redirectUrl, ClientType clientType) {
        boolean redirectUrlExisting = clientInfoRepository.existsByRedirectUrlAndClientType(redirectUrl, clientType);
        if(!redirectUrlExisting) throw new CoreException(ErrorType.NOT_FOUND_DATA);
    }
}
