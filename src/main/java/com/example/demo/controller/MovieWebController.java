package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Movie;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/movies")
public class MovieWebController {

    @Autowired
    com.example.demo.service.MovieService movieService;

    @RequestMapping("/movies")
    public String getWeb(Model containerToView) {
        containerToView.addAttribute("allMovies", movieService.getAllMovies().get());
        return "showMovies";
    }

    @RequestMapping("/newMovieForm")
    public String newMovieForm(Model containerToView) {
        return "newMovieForm";
    }

    @RequestMapping("/createMovie")
    public String createMovie(Movie movie, Model containerToView) {
        movieService.createMovie(movie);
        return "redirect:movies";
    }

    @RequestMapping("/movieDetails")
    public String movieDetails(@RequestParam UUID movieUUID, Model containerToView) {
        containerToView.addAttribute("movie", movieService.findMovieById(movieUUID).get());
        return "movieDetails";
    }

    @RequestMapping("/updateMovieForm")
    public String updateMovieForm(@RequestParam UUID movieUUID, Model containerToView) {
        containerToView.addAttribute("movie", movieService.findMovieById(movieUUID).get());
        return "updateMovieForm";
    }

    @RequestMapping("/updateMovie")
    public String updateMovie(Movie movie, Model containerToView) {
        Optional<Movie> updatedMovie = movieService.updateMovie(movie);
        if(updatedMovie.isPresent()) {
            return "redirect:movies";
        } else {
            return "error";
        }
    }
}

