package com.gameple.core.domain;

import com.gameple.core.entity.UserLoginLog;
import com.gameple.core.enums.ClientType;
import com.gameple.core.enums.LoginLogType;
import com.gameple.core.repository.UserLoginLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginLogService {

    private final UserLoginLogRepository userLoginLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordSuccess(Long userId, String redirectUrl, ClientType clientType) {
        saveLog(userId, redirectUrl, clientType, LoginLogType.SUCCESS);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordFail(Long userId, String redirectUrl, ClientType clientType) {
        saveLog(userId, redirectUrl, clientType, LoginLogType.FAIL);
    }

    private void saveLog(Long userId, String redirectUrl, ClientType clientType, LoginLogType loginLogType) {
        UserLoginLog log = UserLoginLog.builder()
                .userId(userId)
                .redirectUrl(redirectUrl)
                .clientType(clientType)
                .loginLogType(loginLogType)
                .build();

        userLoginLogRepository.save(log);
    }
}
