package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.entity.Partie;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/parties", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Partie.class)
public class PartieRepresentation {

    private final PartieResource pr;

    public PartieRepresentation(PartieResource pr) {
        this.pr = pr;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(pr.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postMethode(@RequestBody Partie partie) {
        partie.setId(UUID.randomUUID().toString());
        Partie saved = pr.save(partie);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
