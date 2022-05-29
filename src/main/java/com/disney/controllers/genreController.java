package com.disney.controllers;


import com.disney.model.Genre;
import com.disney.service.genreService;
import com.disney.service.genreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("api/genre")
public class genreController {

    @Autowired
    genreServiceImpl genreService;

    @PostMapping(headers = "Content-Type= multipart/form-data")
    public ResponseEntity<Genre> save(@RequestParam("genre") String genre, @RequestParam(value = "file") MultipartFile file) throws IOException {
        Genre genre1 = genreService.mapToGenre(genre, file);
        if (!file.isEmpty()) {
            Path dirImagenes = Paths.get("src/main/resources/static/images/genre");
            String rutaAbsoluta = dirImagenes.toFile().getAbsolutePath();
            System.out.println(rutaAbsoluta);

            try {
                byte[] imgBytes = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + file.getOriginalFilename());
                System.out.println(rutaCompleta);
                Files.write(rutaCompleta, imgBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(genreService.save(genre1), HttpStatus.CREATED);
    }


}
