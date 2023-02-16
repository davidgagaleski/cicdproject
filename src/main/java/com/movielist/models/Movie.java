package com.movielist.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// Movie entity class, describing the movies in the database, containing the fields: ID, Name and MovieRating
// id: integer. Unique identifier (key).
// name: String. Describes the movie name
// movieRating: Rating of the movie
@Entity
@Data
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "movie_rating")
    private String movieRating;
}
