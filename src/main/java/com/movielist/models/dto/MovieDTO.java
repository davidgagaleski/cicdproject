package com.movielist.models.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// Data Transfer Object describing the Movie entity class, used for abstraction between the presentation and persistence layers
@Data
public class MovieDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String movieRating;

    public MovieDTO() {
    }

    public MovieDTO(String name, String movieRating) {
        this.name = name;
        this.movieRating = movieRating;
    }
}
