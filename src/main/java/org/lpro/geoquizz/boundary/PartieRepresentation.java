package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.Exception.NotFound;
import org.lpro.geoquizz.entity.Partie;
import org.lpro.geoquizz.entity.Photo;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/{ID}/photos")
    public ResponseEntity<?> getPhotoBySerieId(@PathVariable("ID") String id) throws NotFound {
        if (!par.existsById(id)){
            throw new NotFound("Partie inexistante");
        }
        return new ResponseEntity<>(pr.findByPartieId(id), HttpStatus.OK);
    }

    @PostMapping("/{ID_Partie}/photos/{ID_Photo}")
    public ResponseEntity<?> addPhotoInPartie(@PathVariable("ID_Partie") String id_partie, @PathVariable("ID_Photo") String id_photo) throws NotFound {
        return par.findById(id_partie)
                .map(partie -> {
                    return pr.findById(id_photo)
                            .map(photo -> {
                                photo.setPartie(partie);
                                par.save(partie);
                                return new ResponseEntity<>(HttpStatus.CREATED);
                            }).orElseThrow( () -> new NotFound("Photo inexistante"));
                }).orElseThrow( () -> new NotFound("Partie inexistante"));
    }
}


