package com.disney.service;

import com.disney.model.Genre;
import com.disney.repository.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements BaseService<Genre> {

    @Autowired
    GenreRepository genreRepository;

    public Genre mapToGenre(String genre, String imgSrc) {
        Genre newGenre = new Genre();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            newGenre = objectMapper.readValue(genre, Genre.class);
            newGenre.setImagen(imgSrc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newGenre;
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Genre update(Genre entity, Long id) {
        return null;
    }

    @Override
    public List<Genre> findAll() {
        return null;
    }
}
