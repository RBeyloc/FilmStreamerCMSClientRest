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

    //CRUD: update movie
    @PostMapping(value = "/updateMovie", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        Optional<Movie> movieFound = movieService.findMovieById(movie.getMovieUUID());
        Optional<Movie> movieUpdate = movieFound;

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateMovie");

        if (!movieFound.isPresent()) {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else if (movie.equals(movieFound.get())) {
            headers.add("operationStatus", "no data to update");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            Boolean mustUpdate = false;
            if (!movie.getTitle().equals(movieFound.get().getTitle()) && !movie.getTitle().equals("")) {
                movieUpdate.get().setTitle(movie.getTitle());
                headers.add("title", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getCasting().equals(movieFound.get().getCasting()) && !movie.getCasting().equals("")) {
                movieUpdate.get().setCasting(movie.getCasting());
                headers.add("casting", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getDirecting().equals(movieFound.get().getDirecting()) && !movie.getDirecting().equals("")) {
                movieUpdate.get().setDirecting(movie.getDirecting());
                headers.add("directing", "to be updated");
                mustUpdate = true;
            }
            if ((movie.getReleaseYear() != movieFound.get().getReleaseYear()) && (movie.getReleaseYear() != 0)) {
                movieUpdate.get().setReleaseYear(movie.getReleaseYear());
                headers.add("release year", "to be updated");
                mustUpdate = true;
            }
            if ((movie.getDuration() != movieFound.get().getDuration()) && (movie.getDuration() != 0)) {
                movieUpdate.get().setDuration(movie.getDuration());
                headers.add("duration", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getSynopsis().equals(movieFound.get().getSynopsis()) && !movie.getSynopsis().equals("")) {
                movieUpdate.get().setSynopsis(movie.getSynopsis());
                headers.add("synopsis", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getGenre().equals(movieFound.get().getGenre()) && !movie.getGenre().equals("")) {
                movieUpdate.get().setGenre(movie.getGenre());
                headers.add("genre", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getAgeRating().equals(movieFound.get().getAgeRating()) && !movie.getAgeRating().equals("")) {
                movieUpdate.get().setAgeRating(movie.getAgeRating());
                headers.add("age rating", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getPosterPath().equals(movieFound.get().getPosterPath()) && !movie.getPosterPath().equals("")) {
                movieUpdate.get().setPosterPath(movie.getPosterPath());
                headers.add("poster path", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getVideoPath().equals(movieFound.get().getVideoPath()) && !movie.getVideoPath().equals("")) {
                movieUpdate.get().setVideoPath(movie.getVideoPath());
                headers.add("video path", "to be updated");
                mustUpdate = true;
            }
            if (mustUpdate) {
                headers.add("operationStatus", "updated");
                return ResponseEntity.accepted().headers(headers).body(movieService.updateMovie(movieUpdate.get()).get());
            } else {
                headers.add("operationStatus", "no valid data to update");
                return ResponseEntity.accepted().headers(headers).body(null);
            }
        }
    }

}
