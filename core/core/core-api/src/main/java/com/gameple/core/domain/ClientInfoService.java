package com.gameple.core.domain;

import com.gameple.core.helper.error.CoreException;
import com.gameple.core.helper.error.ErrorType;
import com.gameple.core.repository.ClientInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientInfoService {

    private final ClientInfoRepository clientInfoRepository;

    public void validateRedirectUrl(String redirectUrl) {
        boolean redirectUrlExisting = clientInfoRepository.existsByRedirectUrl(redirectUrl);
        if(!redirectUrlExisting) throw new CoreException(ErrorType.NOT_FOUND_DATA);
    }
}
