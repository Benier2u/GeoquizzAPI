package org.lpro.geoquizzback.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/private", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateRepresentation {

    @GetMapping
    public ResponseEntity<?> getPrivateConnection() {
        return new ResponseEntity<>("Connexion privée", HttpStatus.OK);
    }

}


