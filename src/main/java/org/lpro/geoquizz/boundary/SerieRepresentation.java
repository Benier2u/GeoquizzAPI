package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.Exception.NotFound;
import org.lpro.geoquizz.entity.Photo;
import org.lpro.geoquizz.entity.Serie;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/series", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Serie.class)
public class SerieRepresentation {

    private final SerieResource sr;
    private final PhotoResource pr;

    public SerieRepresentation(SerieResource sr, PhotoResource pr) {
        this.sr = sr;
        this.pr = pr;
    }

    @GetMapping
    public ResponseEntity<?> getAllPhotos() {
        return new ResponseEntity<>(sr.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postSerie(@RequestBody Serie serie) {
        serie.setId(UUID.randomUUID().toString());
        Serie saved = sr.save(serie);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getSerieById(@PathVariable("ID") String id) {
        return Optional.ofNullable(sr.findById(id))
                .filter(Optional::isPresent)
                .map(serie -> new ResponseEntity<>(serie.get(),HttpStatus.OK))
                .orElseThrow( () -> new NotFound("Serie inexistante"));
    }

    @GetMapping("/{ID}/photos")
    public ResponseEntity<?> getSandwichByCategorieId(@PathVariable("ID") String id) throws NotFound {
        if (!sr.existsById(id)){
            throw new NotFound("Serie inexistante");
        }
        return new ResponseEntity<>(pr.findBySerieId(id), HttpStatus.OK);
    }

    @PostMapping("/{ID}/photos")
    public ResponseEntity<?> ajoutSandwich(@PathVariable("ID") String id, @RequestBody Photo photo) throws NotFound {
        return sr.findById(id)
                .map(serie -> {
                    photo.setId(UUID.randomUUID().toString());
                    photo.setSerie(serie);
                    pr.save(photo);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }).orElseThrow( () -> new NotFound("Photo inexistante"));
    }
}
