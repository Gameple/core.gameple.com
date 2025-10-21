package com.gameple.core.domain;

import com.gameple.core.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gameple.core.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

}
