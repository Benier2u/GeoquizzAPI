package org.lpro.geoquizzplayer.boundary;

import org.lpro.geoquizzplayer.entity.Partie;
import org.lpro.geoquizzplayer.entity.Serie;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="geoquizz-back")
@RibbonClient(name="geoquizz-back")
@RequestMapping(value = "/series", produces = MediaType.APPLICATION_JSON_VALUE)
public interface InterfaceSerieRepresentation {

    @GetMapping
    ResponseEntity<?> getSeries();

    @PostMapping
    ResponseEntity<?> postSerie(@RequestBody Serie serie);

    @GetMapping("/{ID}")
    ResponseEntity<?> getSerieById(@PathVariable("ID") String id);

    @GetMapping("/{ID}/parties")
    ResponseEntity<?> getPartieBySerieId(@PathVariable("ID") String id);

    @PostMapping("/{ID}/parties")
    ResponseEntity<?> ajoutPartie(@PathVariable("ID") String id, @RequestBody Partie partie);
}