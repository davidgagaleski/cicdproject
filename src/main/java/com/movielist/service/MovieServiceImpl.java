package com.movielist.service;

import com.movielist.models.Movie;
import com.movielist.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class MovieServiceImpl {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> getMovie(String name) {
        if (name == null || name.isEmpty()) {
            return Arrays.asList(movieRepository.findByName(name));
        }
        return null;
    }

    public Movie createMovie(Movie movie) {
        Movie duplicateMovie = movieRepository.findByName(movie.getName());

        // If the name provided by request is already present, return NULL, and handle on upper layer
        if(duplicateMovie != null) {
            return null;
        }
        return movieRepository.save(movie);
    }
}
