package org.lpro.geoquizzback.controller;

import org.lpro.geoquizzback.boundary.PhotoResource;
import org.lpro.geoquizzback.boundary.SerieResource;
import org.lpro.geoquizzback.entity.Serie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Serie.class)
public class ImageController {

    private final SerieResource sr;
    private final PhotoResource pr;

    public ImageController(SerieResource sr, PhotoResource pr) {
        this.sr = sr;
        this.pr = pr;
    }

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private static String UPLOADED_FOLDER = "./tmp/img/";
    @PostMapping("/images/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
        logger.debug("File upload!");

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("No file", HttpStatus.BAD_REQUEST);
        }
        try {
            saveUploadedFiles(Arrays.asList(uploadfile));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Uploaded - " + uploadfile.getOriginalFilename(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/images/{ID}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> downloadFile(@PathVariable("ID") String id) throws IOException {
        File imgFile = new File("./tmp/img/" +id+".jpg");
        byte[] bFile = Files.readAllBytes(imgFile.toPath());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bFile);
    }

    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }

}