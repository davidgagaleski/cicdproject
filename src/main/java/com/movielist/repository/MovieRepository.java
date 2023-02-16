package com.movielist.repository;

import com.movielist.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring Data JPA repository for the Movie entity used for persisting movie objects to the Database.
// Two additional methods provided
// findByMovieName: Returns a movie with the provided name or NULL if none is provided
@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findByName(String name);
}
