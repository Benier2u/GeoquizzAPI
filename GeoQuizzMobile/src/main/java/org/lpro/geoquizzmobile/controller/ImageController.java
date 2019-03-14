package org.lpro.geoquizzmobile.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@FeignClient(name="geoquizz-back")
@RibbonClient(name="geoquizz-back")
@RequestMapping
public interface ImageController {

    @PostMapping("/images/upload")
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile);

    @GetMapping(value = "/images/{ID}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<?> downloadFile(@PathVariable("ID") String id);

}