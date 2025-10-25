package com.gameple.core.domain;

import com.gameple.core.api.controller.v1.request.AuthenticateUserRequest;
import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.api.controller.v1.response.UserTokenInfo;
import com.gameple.core.entity.CountryInfo;
import com.gameple.core.entity.RefreshToken;
import com.gameple.core.entity.User;
import com.gameple.core.entity.UserProfile;
import com.gameple.core.helper.jwt.JwtUtil;
import com.gameple.core.helper.error.CoreException;
import com.gameple.core.helper.error.ErrorType;
import com.gameple.core.repository.CountryInfoRepository;
import com.gameple.core.repository.RefreshTokenRepository;
import com.gameple.core.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gameple.core.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;

    private final UserLoginLogService userLoginLogService;

    private final UserTokenService userTokenService;

    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final CountryInfoRepository countryInfoRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(CreateUserRequest newUser) {

        boolean passwordMatch = newUser.getPassword().equals(newUser.getPasswordCheck());
        if(!passwordMatch) throw new CoreException(ErrorType.USER_PASSWORD_MISMATCH);

        boolean emailExisting = userRepository.existsByEmail(newUser.getEmail());
        if(emailExisting) throw new CoreException(ErrorType.EMAIL_ALREADY_EXIST);

        boolean nickNameExisting = userProfileRepository.existsByNickName(newUser.getNickName());
        if(nickNameExisting) throw new CoreException(ErrorType.NICKNAME_ALREADY_EXIST);

        CountryInfo countryInfoEntity = countryInfoRepository.findById(newUser.getCountryId())
                .orElseThrow(() -> new CoreException(ErrorType.COUNTRY_NOT_EXIST));

        String encodedPassword = passwordEncoder.encode(newUser.getPassword());

        User user = User.builder()
                .email(newUser.getEmail())
                .passwordHash(encodedPassword)
                .build();

        User savedUser = userRepository.save(user);

        UserProfile userProfile = UserProfile.builder()
                .userId(savedUser.getId())
                .nickName(newUser.getNickName())
                .countryCode(countryInfoEntity.getIso2())
                .build();

        userProfileRepository.save(userProfile);

        return newUser.getEmail();
    }

    @Transactional
    public UserTokenInfo authenticateUser(AuthenticateUserRequest authenticateUserInfo) {

        User userEntity = userRepository.findByEmail(authenticateUserInfo.getEmail())
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND_DATA));

        boolean passwordMatching = passwordEncoder.matches(authenticateUserInfo.getPassword(), userEntity.getPasswordHash());
        if(!passwordMatching) {
            userLoginLogService.recordFail(userEntity.getId());
            throw new CoreException(ErrorType.USER_PASSWORD_MISMATCH);
        }

        String accessToken = userTokenService.generateAccessToken(userEntity);
        String refreshToken = userTokenService.generateRefreshToken(userEntity);

        userLoginLogService.recordSuccess(userEntity.getId());

        UserTokenInfo userTokenInfo = UserTokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return userTokenInfo;
    }

    @Transactional
    public String refreshUserToken(String refreshToken) {
        RefreshToken refreshTokenEntity = refreshTokenRepository.findTopByToken(refreshToken)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND_DATA));

        boolean refreshTokenExpiry = refreshTokenEntity.getExpiryDate().isBefore(Instant.now());
        if(refreshTokenExpiry) throw new CoreException(ErrorType.EXPIRED_TOKEN);

        refreshTokenRepository.delete(refreshTokenEntity);

        User userEntity = userRepository.findById(refreshTokenEntity.getUserId())
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND_DATA));

        String accessToken = userTokenService.generateAccessToken(userEntity);

        return accessToken;
    }
}
