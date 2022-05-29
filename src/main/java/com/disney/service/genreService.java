package com.disney.service;

import com.disney.model.Genre;

import java.util.List;

public interface genreService {

    Genre save(Genre genre);

    Genre findById(Long id);

    void delete(Long id);

    List<Genre> findAll();

}
