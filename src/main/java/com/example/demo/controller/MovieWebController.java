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

    /*@RequestMapping("/updateMovie")
    public String updateMovie(Movie movie, Model containerToView) {
        Optional<Movie> updatedMovie = movieService.updateMovie(movie);
        if(updatedMovie.isPresent()) {
            return "redirect:movies";
        } else {
            return "error";
        }
    }*/

    @RequestMapping("/updateMovie")
    public String updateMovie(Movie movie, Model containerToView) {
        Optional<Movie> movieFound = movieService.findMovieById(movie.getMovieUUID());
        Optional<Movie> movieUpdate = movieFound;

        if (!movieFound.isPresent()) {
            containerToView.addAttribute("errorMessage", "Movie not found");
            return "error";
        } else if (movie.equals(movieFound.get())) {
            containerToView.addAttribute("errorMessage", "No data to update");
            return "error";
        } else {
            Boolean mustUpdate = false;
            if (!movie.getTitle().equals(movieFound.get().getTitle()) && !movie.getTitle().equals("")) {
                movieUpdate.get().setTitle(movie.getTitle());
                mustUpdate = true;
            }
            if (!movie.getCasting().equals(movieFound.get().getCasting()) && !movie.getCasting().equals("")) {
                movieUpdate.get().setCasting(movie.getCasting());
                mustUpdate = true;
            }
            if (!movie.getDirecting().equals(movieFound.get().getDirecting()) && !movie.getDirecting().equals("")) {
                movieUpdate.get().setDirecting(movie.getDirecting());
                mustUpdate = true;
            }
            if ((movie.getReleaseYear() != movieFound.get().getReleaseYear()) && (movie.getReleaseYear() != 0)) {
                movieUpdate.get().setReleaseYear(movie.getReleaseYear());
                mustUpdate = true;
            }
            if ((movie.getDuration() != movieFound.get().getDuration()) && (movie.getDuration() != 0)) {
                movieUpdate.get().setDuration(movie.getDuration());
                mustUpdate = true;
            }
            if (!movie.getSynopsis().equals(movieFound.get().getSynopsis()) && !movie.getSynopsis().equals("")) {
                movieUpdate.get().setSynopsis(movie.getSynopsis());
                mustUpdate = true;
            }
            if (!movie.getGenre().equals(movieFound.get().getGenre()) && !movie.getGenre().equals("")) {
                movieUpdate.get().setGenre(movie.getGenre());
                mustUpdate = true;
            }
            if (!movie.getAgeRating().equals(movieFound.get().getAgeRating()) && !movie.getAgeRating().equals("")) {
                movieUpdate.get().setAgeRating(movie.getAgeRating());
                mustUpdate = true;
            }
            if (!movie.getPosterPath().equals(movieFound.get().getPosterPath()) && !movie.getPosterPath().equals("")) {
                movieUpdate.get().setPosterPath(movie.getPosterPath());
                mustUpdate = true;
            }
            if (!movie.getVideoPath().equals(movieFound.get().getVideoPath()) && !movie.getVideoPath().equals("")) {
                movieUpdate.get().setVideoPath(movie.getVideoPath());
                mustUpdate = true;
            }
            if (mustUpdate) {
                containerToView.addAttribute("modifiedMovie", movieService.updateMovie(movieUpdate.get()));
                return "redirect:movies";
            } else {
                containerToView.addAttribute("errorMessage", "No valid data to update");
                return "error";
            }
        }
    }
}

