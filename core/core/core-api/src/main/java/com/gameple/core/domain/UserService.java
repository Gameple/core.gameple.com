package com.gameple.core.domain;

import com.gameple.core.api.controller.v1.request.AuthenticateUserRequest;
import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.api.controller.v1.response.AuthenticateUserResponse;
import com.gameple.core.api.controller.v1.response.UserTokenInfo;
import com.gameple.core.entity.RefreshToken;
import com.gameple.core.entity.User;
import com.gameple.core.entity.UserLoginLog;
import com.gameple.core.entity.UserProfile;
import com.gameple.core.enums.LoginLogType;
import com.gameple.core.helper.JwtUtil;
import com.gameple.core.helper.error.CoreException;
import com.gameple.core.helper.error.ErrorType;
import com.gameple.core.repository.RefreshTokenRepository;
import com.gameple.core.repository.UserLoginLogRepository;
import com.gameple.core.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gameple.core.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    private final UserLoginLogService userLoginLogService;

    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(CreateUserRequest newUser) {

        boolean passwordMatch = newUser.getPassword().equals(newUser.getPasswordCheck());
        if(!passwordMatch) throw new CoreException(ErrorType.USER_PASSWORD_MISMATCH);

        boolean emailExisting = userRepository.existsByEmail(newUser.getEmail());
        if(emailExisting) throw new CoreException(ErrorType.EMAIL_ALREADY_EXIST);

        boolean nickNameExisting = userProfileRepository.existsByNickName(newUser.getNickName());
        if(nickNameExisting) throw new CoreException(ErrorType.NICKNAME_ALREADY_EXIST);

        String encodedPassword = passwordEncoder.encode(newUser.getPassword());

        User user = User.builder()
                .email(newUser.getEmail())
                .passwordHash(encodedPassword)
                .build();

        User savedUser = userRepository.save(user);

        UserProfile userProfile = UserProfile.builder()
                .userId(savedUser.getId())
                .nickName(newUser.getNickName())
                .build();

        userProfileRepository.save(userProfile);

        return newUser.getEmail();
    }

    @Transactional
    public UserTokenInfo authenticateUser(AuthenticateUserRequest authenticateUserInfo) {

        User targetUser = userRepository.findByEmail(authenticateUserInfo.getEmail())
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND_DATA));

        boolean passwordMatching = passwordEncoder.matches(authenticateUserInfo.getPassword(), targetUser.getPasswordHash());
        if(!passwordMatching) {
            userLoginLogService.recordFail(targetUser.getId());
            throw new CoreException(ErrorType.USER_PASSWORD_MISMATCH);
        }

        String accessToken = jwtUtil.generateToken(targetUser.getEmail());
        String refreshToken = UUID.randomUUID().toString();

        RefreshToken newRefreshToken = RefreshToken.builder()
                .userId(targetUser.getId())
                .token(refreshToken)
                .build();

        refreshTokenRepository.save(newRefreshToken);

        userLoginLogService.recordSuccess(targetUser.getId());

        UserTokenInfo userTokenInfo = UserTokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return userTokenInfo;
    }
}
