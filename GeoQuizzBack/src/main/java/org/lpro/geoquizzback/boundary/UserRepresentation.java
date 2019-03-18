package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.entity.User;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(User.class)
public class UserRepresentation {

    private final UserResource ur;

    public UserRepresentation(UserResource ur) {
        this.ur = ur;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(ur.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(hash(user.getPassword()));
        User saved = ur.save(user);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/login/{ID}/{PASS}")
    public ResponseEntity<?> login(@PathVariable("ID") String id,@PathVariable("PASS") String password) {

        Optional<User> user = ur.findById(id);
        if (user.isPresent()) {
            if (verifyHash(password, user.get().getPassword())) {
                return new ResponseEntity<>("true", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("false", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>("Utilisateur inexistant", HttpStatus.NOT_FOUND);
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(15));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
