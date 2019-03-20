package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserResource extends CrudRepository<User, String> {
    User findByUsername(String username);
    @Query(" select u from User u " +
            " where u.username = ?1")
    Optional<User> findUserWithName(String username);
}
