package org.lpro.geoquizzback.boundary;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.lpro.geoquizzback.Exception.NotFound;
import org.lpro.geoquizzback.entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/series", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Serie.class)
public class SerieRepresentation {

    @Value("${addresse.for.open.docker.to.the.world}")
    private String addresse;

    private final SerieResource sr;
    private final PhotoResource pr;
    private final PartieResource par;

    public SerieRepresentation(SerieResource sr, PhotoResource pr, PartieResource par) {
        this.sr = sr;
        this.pr = pr;
        this.par = par;
    }

    @GetMapping
    public ResponseEntity<?> getSeries() {
        Iterable<Serie> series = sr.findAll();

        ArrayList<SerieNoDetail> serieNoDetail = new ArrayList<>();

        series.forEach(ele -> {
            SerieNoDetail serie = new SerieNoDetail();
            serie.setId(ele.getId());
            serie.setMap_refs(ele.getMap_refs());
            serie.setVille(ele.getVille());
            serie.setDist(ele.getDist());
            serieNoDetail.add(serie);
        });
        return new ResponseEntity<>(serieNoDetail, HttpStatus.OK);
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
    public ResponseEntity<?> getPhotoBySerieId(@PathVariable("ID") String id) throws NotFound {
        if (!sr.existsById(id)){
            throw new NotFound("Serie inexistante");
        }
        return new ResponseEntity<>(pr.findBySerieId(id), HttpStatus.OK);
    }

    @PostMapping("/{ID}/photos")
    public ResponseEntity<?> ajoutPhoto(@PathVariable("ID") String id, @RequestBody Photo photo) throws NotFound {

        return sr.findById(id)
                .map(serie -> {
                    photo.setSerie(serie);
                    photo.setUrl(addresse + "/images/" + photo.getId());
                    pr.save(photo);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }).orElseThrow( () -> new NotFound("Photo inexistante"));
    }

    @GetMapping("/{ID}/parties")
    public ResponseEntity<?> getPartieBySerieId(@PathVariable("ID") String id) throws NotFound {
        if (!sr.existsById(id)){
            throw new NotFound("Serie inexistante");
        }
        return new ResponseEntity<>(par.findBySerieId(id), HttpStatus.OK);
    }

    @PostMapping("/{ID}/parties")
    public ResponseEntity<?> ajoutPartie(@PathVariable("ID") String id, @RequestBody Partie partie) throws NotFound {
        return sr.findById(id)
                .map(serie -> {
                    partie.setId(UUID.randomUUID().toString());
                    String jwtToken = Jwts.builder().setSubject(partie.getId()).claim("roles", "serie").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                    partie.setSerie(serie);
                    partie.setToken(jwtToken);
                    partie.setScore(0);
                    partie.setStatus("En cours");

                    Set<Photo> photos = new HashSet<Photo>();
                    List<Photo> listPhoto = pr.findRandomPhoto(serie);
                    int taille = listPhoto.size();
                    partie.setNb_photos(taille);

                    for (int i = 0; i < taille; i++) {
                        photos.add(listPhoto.get(i));
                    }
                    partie.setPhotos(photos);
                    par.save(partie);

                    PartieNoDetail partieNoDetail = new PartieNoDetail();
                    partieNoDetail.setId(partie.getId());
                    partieNoDetail.setToken(partie.getToken());

                    return new ResponseEntity<Object>(partieNoDetail,HttpStatus.CREATED);
                }).orElseThrow( () -> new NotFound("Serie inexistante"));
    }
}