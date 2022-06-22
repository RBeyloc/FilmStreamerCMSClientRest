package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/movies")
public class MovieRestController {

    @Autowired
    MovieService movieService;

    //here we are creating our end-point rest API
    //CRUD: read all movies
    @GetMapping("/movies")
    public ResponseEntity<Iterable<Movie>> getAllMovies() {
        Optional<Iterable<Movie>> moviesRetrieved = movieService.getAllMovies();

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "movies");

        if (moviesRetrieved.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(moviesRetrieved.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: delete movie by id
    @DeleteMapping("/deleteMovie")
    public ResponseEntity<Movie> deleteMovie(@RequestParam UUID movieId) {
        Optional<Movie> movieFound = movieService.deleteMovieById(movieId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteMovie");

        if (movieFound.isPresent()) {
            headers.add("operationStatus", "deleted");
            return ResponseEntity.accepted().headers(headers).body(movieFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

}
