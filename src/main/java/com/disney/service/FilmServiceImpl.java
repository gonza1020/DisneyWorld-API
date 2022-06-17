package com.disney.service;
import com.disney.model.Character;
import com.disney.model.Film;
import com.disney.repository.FilmRepository;
import com.disney.repository.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if(checkNull(film)){
            return movieRepository.save(film);
        }
        return null;
    }

    @Override
    public Film findById(Long id) {
        return  movieRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {

        Optional<Film> film = movieRepository.findById(id);

        if(film.isPresent()){
            for(Character c: film.get().getCharacters()){
                c.getFilms().remove(film.get());
            }
            movieRepository.delete(film.get());
        }
    }

    @Override
    public Film update(Film entity, Long id) {

        Optional<Film> film = movieRepository.findById(id);

        if(film.isPresent()){
            film.get().setTitle(entity.getTitle());
            film.get().setReleaseDate(entity.getReleaseDate());
            film.get().setImage(entity.getImage());
            film.get().setGenre(entity.getGenre());
            film.get().setCalification((entity.getCalification()));
            if(checkNull(film.get())){
                return null;
            }
            return movieRepository.save(film.get());
        }
        return null;
    }

    public boolean checkNull(Film film){
        if(film.getCalification() == 0) return true;
        if(film.getGenre() == null) return true;
        if(film.getImage() == null) return true;
        if (film.getReleaseDate() == null) return true;
        if(film.getTitle() == null) return true;
        return (film.getType() == null);
    }

    @Override
    public List<Film> findAll() {
        return movieRepository.findAll();
    }
}

