package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.*;

import com.example.demo.model.Movie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    RestTemplate restTemplate = new RestTemplate();

    public Optional<Iterable<Movie>> getAllMovies() {
        ResponseEntity<List<Movie>> response = restTemplate.exchange("http://localhost:8083/api/movies/movies",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {
                });
        List<Movie> movies = response.getBody();
        return Optional.of(movies);
    }

    public Optional<Movie> findMovieById(UUID movieUUID) {
        ResponseEntity<Movie> response = restTemplate.exchange("http://localhost:8083/api/movies/getMovie/" + movieUUID,
                HttpMethod.GET, null, new ParameterizedTypeReference<Movie>() {
                });
        Movie movie = response.getBody();
        return Optional.of(movie);
    }

    public Optional<Movie> deleteMovieById(UUID movieUUID) {
        ResponseEntity<Movie> response = restTemplate.exchange("http://localhost:8083/api/movies/deleteMovie/" + movieUUID,
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Movie>() {
                });
        Movie movie = response.getBody();
        return Optional.of(movie);
    }

    public Optional<Movie> createMovie(Movie movie) {
        String url = "http://localhost:8083/api/movies/createMovie";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Movie> request = new HttpEntity<>(movie, headers);

        ResponseEntity<Movie> response = restTemplate.postForEntity(url, request, Movie.class);

        Movie newMovie = response.getBody();
        return Optional.of(newMovie);
    }

    public Optional<Movie> updateMovie(Movie movie) {
        String url = "http://localhost:8083/api/movies/updateMovie";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Movie> request = new HttpEntity<>(movie, headers);

        ResponseEntity<Movie> response = restTemplate.postForEntity(url, request, Movie.class);

        Movie newMovie = response.getBody();
        return Optional.of(newMovie);
    }

}