package com.disney.service;

import com.disney.model.Genre;
import com.disney.repository.GenreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class genreServiceImpl implements genreService {


    GenreRepository genreRepository;

    public Genre mapToGenre(String genre, MultipartFile file) {
        Genre newGenre = new Genre();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            newGenre = objectMapper.readValue(genre, Genre.class);
            newGenre.setImagen(file.getOriginalFilename());
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
    public List<Genre> findAll() {
        return null;
    }
}
