package com.example.codehive.security;

import com.example.codehive.entity.User;
import com.example.codehive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new UsernameNotFoundException("존재하지 않는 유저입니다: " + userId);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())                // 로그인 ID
                .password(user.getPassword())              // 암호화된 비밀번호
                .roles(user.getRole().name())              // Enum → "USER" or "ADMIN"
                .build();
    }
}