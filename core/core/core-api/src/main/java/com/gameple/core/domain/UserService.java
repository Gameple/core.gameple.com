package com.gameple.core.domain;

import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.entity.User;
import com.gameple.core.entity.UserProfile;
import com.gameple.core.helper.error.CoreException;
import com.gameple.core.helper.error.ErrorType;
import com.gameple.core.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gameple.core.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

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
}
