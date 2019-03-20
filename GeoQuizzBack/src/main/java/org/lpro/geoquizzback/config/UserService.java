package org.lpro.geoquizzback.config;

import java.util.Objects;

import org.lpro.geoquizzback.boundary.UserResource;
import org.lpro.geoquizzback.entity.User;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserResource userRepository;

    @Autowired
    public UserService(UserResource userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}

