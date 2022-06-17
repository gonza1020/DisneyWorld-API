package com.disney.controllers;

import com.disney.base.BaseController;
import com.disney.model.Film;
import com.disney.service.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/movies")
public class FilmController implements BaseController<Film,Long> {

    @Autowired
    FilmServiceImpl movieService;

    @Override
    @PostMapping()
    public ResponseEntity<Film> save(String film, MultipartFile file) throws Exception {

        if (!file.isEmpty()) {
            Path dirImagenes = Paths.get("src/main/resources/static/images/movies");
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
            Film film1 = movieService.mapToMovie(film, imgSrc);
            return new ResponseEntity<>(movieService.save(film1), HttpStatus.CREATED);
        }
        throw new Exception("Error al subir la imagen");
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Film> findById(@PathVariable Long id) {
        return  new ResponseEntity<>(movieService.findById(id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("{id}")
    public void delete( @PathVariable Long id) {
        movieService.delete(id);
    }

    @Override
    @GetMapping()
    public List<Film> findAll() {
        return movieService.findAll();
    }

    @PutMapping("{id}")
    public Film update(@PathVariable Long id, Film film) {
        return movieService.update(film, id);
    }
}
