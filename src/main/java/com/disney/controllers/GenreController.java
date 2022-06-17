package com.disney.controllers;


import com.disney.model.Genre;
import com.disney.service.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("api/genre")
public class GenreController {

    @Autowired
    GenreServiceImpl genreService;

    @PostMapping(headers = "Content-Type= multipart/form-data")
    public ResponseEntity<Genre> create(@RequestPart("genre") String genre, @RequestPart(value = "file") MultipartFile file) throws Exception {

        if (!file.isEmpty()) {
            Path dirImagenes = Paths.get("src/main/resources/static/images/genre");
            String rutaAbsoluta = dirImagenes.toFile().getAbsolutePath();
            System.out.println(rutaAbsoluta);

            try {
                byte[] imgBytes = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + file.getOriginalFilename());
                System.out.println(file.getName());
                Files.write(rutaCompleta, imgBytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
            String imgSrc = file.getOriginalFilename();
            Genre genre1 = genreService.mapToGenre(genre, imgSrc);
            return new ResponseEntity<>(genreService.save(genre1), HttpStatus.CREATED);
        }
        throw new Exception("Error al subir la imagen");
    }
}
