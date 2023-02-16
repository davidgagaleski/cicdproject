package com.movielist.controller;

import com.movielist.models.Movie;
import com.movielist.exceptions.ApiError;
import com.movielist.models.dto.MovieDTO;
import com.movielist.service.MovieServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/movielist")
@Validated
public class MovieController {

    // Used for simple JSON<->POJO conversion
    private final ModelMapper modelMapper;

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    // Index endpoint. When called, returns a list of all movies persisted in the database

    @GetMapping("/")
    public List<MovieDTO> getAll(){
        return movieService.findAll()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    // Endpoint for searching for movies by NAME
    // Parameter: name (String, NOT REQUIRED)
    //
    // Returns a list of movies that contain the name query parameter in their name (if provided)
    @GetMapping("/movies")
    public ResponseEntity<Object> getSpecifiedMovie(@RequestParam(value = "name",required = false) String name) {
        List<Movie> movies = movieService.getMovie(name);
        if(movies == null){
            ApiError apiError = new ApiError("You must provide name");
            return ResponseEntity.badRequest().body(apiError);
        }
        return ResponseEntity.ok(movies
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList()));
    }

    // Endpoint for creating and persisting a movie
    // Parameter: JSON body with two attributes: name, movieRating (both Strings)
    //
    // Constraints: name must not be a NULL value or a BLANK string
    //
    // In case of error, returns a response with status BAD_REQUEST, with a JSON body describing the cause
    // In case of success, returns a response with status CREATED, with a JSON body of the saved movie
    @PostMapping("/movies")
    public ResponseEntity<Object> createMovie(@Valid @RequestBody MovieDTO movieDTO) {

        Movie movie = modelMapper.map(movieDTO, Movie.class);

        Movie result = movieService.createMovie(movie);

        if(result == null){
            ApiError apiError = new ApiError("Movie already exists");
            return ResponseEntity.badRequest().body(apiError);
        }

        MovieDTO response = modelMapper.map(result, MovieDTO.class);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
