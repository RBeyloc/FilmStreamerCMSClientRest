package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Movie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    RestTemplate restTemplate = new RestTemplate();

    public Optional<Iterable<Movie>> getAllMovies() {
        ResponseEntity<List<Movie>> response = restTemplate.exchange("http://localhost:8083/api/movies/movies",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {});
        List<Movie> movies = response.getBody();
        return Optional.of(movies);
    }

    public Optional<Movie> findMovieById(UUID movieUUID) {
        ResponseEntity<Movie> response = restTemplate.exchange("http://localhost:8083/api/movies/getMovie/" + movieUUID,
                HttpMethod.GET, null, new ParameterizedTypeReference<Movie>() {});
        Movie movie = response.getBody();
        return Optional.of(movie);
    }

    public Optional<Movie> deleteMovieById(UUID movieUUID) {
        ResponseEntity<Movie> response = restTemplate.exchange("http://localhost:8083/api/movies/deleteMovie/" + movieUUID,
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Movie>() {});
        Movie movie = response.getBody();
        return Optional.of(movie);
    }

   public Optional<Movie> createMovie(Movie movie){
        ResponseEntity<Movie> response = restTemplate.exchange("http://localhost:8083/api/movies/addMovie/",
                HttpMethod.POST, null, new ParameterizedTypeReference<Movie>() {}, movie);
        Movie newMovie = response.getBody();
        return Optional.of(newMovie);
    }

}