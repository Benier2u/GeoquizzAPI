package org.lpro.geoquizzmobile.boundary;

import org.lpro.geoquizzmobile.entity.Photo;
import org.lpro.geoquizzmobile.entity.Serie;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="geoquizz-back")
@RibbonClient(name="geoquizz-back")
@RequestMapping(value = "/series", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SerieRepresentation {

    @GetMapping
    ResponseEntity<?> getSeries();

    @PostMapping
    ResponseEntity<?> postSerie(@RequestBody Serie serie);

    @PostMapping("/{ID}/photos")
    ResponseEntity<?> ajoutPhoto(@PathVariable("ID") String id, @RequestBody Photo photo);

}