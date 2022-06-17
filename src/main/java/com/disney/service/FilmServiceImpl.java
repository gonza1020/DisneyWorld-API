package com.disney.service;
import com.disney.model.Film;
import com.disney.repository.FilmRepository;
import com.disney.repository.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements BaseService<Film> {

    @Autowired
    FilmRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    public Film mapToMovie(String movie, String imgSrc) {
        Film newMovie= new Film();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            newMovie = objectMapper.readValue(movie, Film.class);
            newMovie.setImage(imgSrc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newMovie;
    }

    @Override
    public Film save(Film film) {
        return movieRepository.save(film);
    }

    @Override
    public Film findById(Long id) {
        Film film = movieRepository.findById(id).get();

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Film update(Film entity, Long id) {
        return null;
    }

    @Override
    public List<Film> findAll() {
        return movieRepository.findAll();
    }
}

