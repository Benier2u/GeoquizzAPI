package org.lpro.geoquizzback.controller;

import java.util.List;
import org.lpro.geoquizzback.entity.User;

public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();
}
