package org.lpro.geoquizzplayer.boundary;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="geoquizz-back")
@RibbonClient(name="geoquizz-back")
@RequestMapping(value = "/parties", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PartieRepresentation {

    @GetMapping("/{ID}")
    ResponseEntity<?> getPartieById(@PathVariable("ID") String id, @RequestHeader(value = "x-lbs-token") String headerToken);

    @DeleteMapping("/{ID}")
    ResponseEntity<?> deletePartieById(@PathVariable("ID") String id, @RequestHeader(value = "x-lbs-token") String headerToken);
}