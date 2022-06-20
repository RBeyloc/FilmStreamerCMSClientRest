package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.List;
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

    public Optional<Movie> createMovie(Movie movie){
        ResponseEntity<Movie> response = restTemplate.exchange("http://localhost:8083/api/movies/addMovie",
                HttpMethod.POST, null, new ParameterizedTypeReference<Movie>() {}, movie);
        Movie newMovie = response.getBody();
        return Optional.of(newMovie);
    }

    /*public Optional<Movie> findMovieById(UUID id){
        return movieRepository.findById(id);
    }

    public Optional<Movie> deleteMovieById(UUID id){
        //Find out IF this id-movie IS in our DB
        Optional<Movie> movieFound = movieRepository.findById(id);
        if(movieFound.isPresent()) {
            movieRepository.deleteById(id);
            return Optional.of(movieFound.get());
        } else {
            return null;
        }
    }

    public Optional<Movie> updateMovie(Movie movie) {
        Optional<Movie> movieFound = movieRepository.findById(movie.getMovieUUID());
        if(movieFound.isPresent()) {
            return Optional.of(movieRepository.save(movie));
        } else {
            return null;
        }
    }

    public Optional<Iterable<Movie>> findMoviesByTitle(String title){
        return movieRepository.findMoviesByTitle(title);
    }

    public Optional<Movie> deleteMovieByTitle(String title){
        return movieRepository.deleteMovieByTitle(title);
    }

    public int count() {
        return (int) movieRepository.count();
    }*/

}