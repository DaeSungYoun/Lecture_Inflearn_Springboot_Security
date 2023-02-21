package com.ydskingdom.jwt.service;

import com.ydskingdom.jwt.model.User;
import com.ydskingdom.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public String join(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입완료";
    }
}
