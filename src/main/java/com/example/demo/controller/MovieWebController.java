package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.Movie;

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
    public String deleteMovie(@RequestParam long movieId, Model containerToView) {
        movieService.deleteMovieById(movieId);
        containerToView.addAttribute("allMovies", movieService.getAllMovies().get());
        return "showMovies";
    }

    @RequestMapping("/newMovieForm")
    public String newMovieForm(Model containerToView) {
        return "newMovieForm";
    }

    @RequestMapping("/createMovie")
    public String createMovie(@RequestParam String title, @RequestParam String releaseYear,
                              @RequestParam String duration, @RequestParam String directing,
                              @RequestParam String casting, @RequestParam String synopsis,
                              @RequestParam String genre, @RequestParam String ageRating,
                              @RequestParam String posterPath,  @RequestParam String videoPath,
                              Model containerToView) {
        Movie newMovie = new Movie(title, Integer.valueOf(releaseYear), Integer.valueOf(duration),
                directing, casting, synopsis, genre, ageRating, posterPath, videoPath);
        movieService.createMovie(newMovie);
        containerToView.addAttribute("allMovies", movieService.getAllMovies().get());
        return "showMovies";
    }

}
