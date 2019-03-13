package org.lpro.geoquizz.controller;

import org.lpro.geoquizz.boundary.PartieResource;
import org.lpro.geoquizz.boundary.PhotoResource;
import org.lpro.geoquizz.boundary.SerieResource;
import org.lpro.geoquizz.entity.Serie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Serie.class)
public class ImageController {

    private final SerieResource sr;
    private final PhotoResource pr;
    private final PartieResource par;

    public ImageController(SerieResource sr, PhotoResource pr, PartieResource par) {
        this.sr = sr;
        this.pr = pr;
        this.par = par;
    }

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private static String UPLOADED_FOLDER = "./tmp/img/";

    @PostMapping("images/upload")
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
    public ResponseEntity<?> uploadFile(@PathVariable("ID") String id) throws IOException {

//        String current = new java.io.File( "." ).getCanonicalPath();
//        System.out.println("Current dir:"+current);

        File imgFile = new File("./tmp/img/" +id+".jpg");
        byte[] bFile = Files.readAllBytes(imgFile.toPath());
//        byte[] bytesArray = new byte[(int) imgFile.length()];
//
//        FileInputStream fis = new FileInputStream(imgFile);
//        fis.read(bytesArray); //read file into bytes[]
//        fis.close();

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