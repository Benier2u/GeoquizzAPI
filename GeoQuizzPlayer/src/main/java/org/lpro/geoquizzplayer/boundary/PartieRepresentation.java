package org.lpro.geoquizzplayer.boundary;

import org.lpro.geoquizzplayer.Exception.NotFound;
import org.lpro.geoquizzplayer.entity.Partie;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/parties", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Partie.class)
public class PartieRepresentation {

    private final PartieResource par;
    private final PhotoResource pr;

    public PartieRepresentation(PartieResource par, PhotoResource pr) {
        this.par = par;
        this.pr = pr;
    }

    @GetMapping
    public ResponseEntity<?> getAllParties() {
        return new ResponseEntity<>(par.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getPartieById(@PathVariable("ID") String id) {
        return Optional.ofNullable(par.findById(id))
                .filter(Optional::isPresent)
                .map(partie -> new ResponseEntity<>(partie.get(),HttpStatus.OK))
                .orElseThrow( () -> new NotFound("Partie inexistante"));
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deletePartieById(@PathVariable("ID") String id) {
        return par.findById(id)
                .map(partie -> {
                    par.delete(partie);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }).orElseThrow( () -> new NotFound("Partie inexistante"));
    }

    @GetMapping("/{ID}/photos")
    public ResponseEntity<?> getPhotoBySerieId(@PathVariable("ID") String id) throws NotFound {
        if (!par.existsById(id)){
            throw new NotFound("Partie inexistante");
        }
        return new ResponseEntity<>(pr.findByPartiesId(id), HttpStatus.OK);
    }
}


