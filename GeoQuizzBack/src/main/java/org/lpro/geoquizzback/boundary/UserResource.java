package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserResource extends CrudRepository<User, String> {
    User findByUsername(String username);
}
