package com.disney.controllers;

import com.disney.model.Genre;
import com.disney.service.GenreServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreControllerTest {

    @Autowired
    GenreServiceImpl genreService;

    String genre = "{\"name\":\"Comedia\"}";
    String file = "comedy.jpg";
    @Test
    void save() {
        Genre genre1 = genreService.mapToGenre(genre, file);
        assertEquals("Comedia", genre1.getName());
        assertEquals("comedy.jpg", genre1.getImagen());
    }
}