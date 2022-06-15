package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.Movie;

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

    @RequestMapping("/deleteMovie")
    public String deleteMovie(@RequestParam String movieUUID, Model containerToView) {
        if(movieService.findMovieById(UUID.fromString(movieUUID)).isPresent()) {
            movieService.deleteMovieById(UUID.fromString(movieUUID));
            //containerToView.addAttribute("allMovies", movieService.getAllMovies().get());
            return "redirect:movies";
        } else {
            return "error";
        }
    }

    @RequestMapping("/newMovieForm")
    public String newMovieForm(Model containerToView) {
        return "newMovieForm";
    }

    @RequestMapping("/createMovie")
    public String createMovie(Movie movie, Model containerToView) {
        movieService.createMovie(movie);
        containerToView.addAttribute("allMovies", movieService.getAllMovies().get());
        return "redirect:movies";
    }

    @RequestMapping("/updateMovieForm")
    public String updateMovieForm(@RequestParam UUID movieUUID, Model containerToView) {
        containerToView.addAttribute("movie", movieService.findMovieById(movieUUID).get());
        return "updateMovieForm";
    }

    @RequestMapping("/updateMovie")
    public String updateMovie(Movie movie, Model containerToView) {
        movieService.updateMovie(movie);
        containerToView.addAttribute("allMovies", movieService.getAllMovies().get());
        return "redirect:movies";
    }

    @RequestMapping("/movieDetails")
    public String movieDetails(@RequestParam UUID movieUUID, Model containerToView) {
        containerToView.addAttribute("movie", movieService.findMovieById(movieUUID).get());
        return "movieDetails";
    }

}
