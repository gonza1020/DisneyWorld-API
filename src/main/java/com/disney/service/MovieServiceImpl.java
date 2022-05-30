package com.disney.service;

import com.disney.model.Movie;
import com.disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements BaseService<Movie> {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return null;
    }

    @Override
    public Movie findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Movie> findAll() {
        return null;
    }
}

