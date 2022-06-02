package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> deleteMovieByTitle(String title);

    Optional<Iterable<Movie>> findMoviesByTitle(String title);
}
