package org.lpro.geoquizzback.controller.impl;

import org.lpro.geoquizzback.boundary.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.lpro.geoquizzback.entity.User;
import org.lpro.geoquizzback.controller.GenericService;

@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserResource userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }
}
