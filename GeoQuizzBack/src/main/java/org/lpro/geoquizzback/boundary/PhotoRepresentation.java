package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.Exception.NotFound;
import org.lpro.geoquizzback.entity.Photo;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/photos", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Photo.class)
public class PhotoRepresentation {

    private final PhotoResource pr;

    public PhotoRepresentation(PhotoResource pr) {
        this.pr = pr;
    }

    @GetMapping
    public ResponseEntity<?> getAllPhotos() {
        return new ResponseEntity<>(pr.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getPhotoById(@PathVariable("ID") String id) {
        return Optional.ofNullable(pr.findById(id))
                .filter(Optional::isPresent)
                .map(photo -> new ResponseEntity<>(photo.get(),HttpStatus.OK))
                .orElseThrow( () -> new NotFound("Photo inexistante"));
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteMethode(@PathVariable("ID") String id) throws NotFound {
        return pr.findById(id)
                .map(photo -> {
                    pr.delete(photo);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }).orElseThrow( () -> new NotFound("Photo inexistante"));
    }
}
