package com.ydskingdom.security.config.auth;

import com.ydskingdom.security.model.User;
import com.ydskingdom.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면
// 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행
// 이건 그냥 규칙
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {
            return new PrincipalDetails(user);
        }
        return null;
    }
}
