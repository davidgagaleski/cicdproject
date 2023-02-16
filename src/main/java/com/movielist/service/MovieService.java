package com.movielist.service;

import com.movielist.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> getMovie(String name);

    Movie createMovie(Movie movie);
}
