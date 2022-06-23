package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Movie implements Serializable {

    private UUID movieUUID;
    private String title;
    private int releaseYear;
    private int duration;
    private String directing;
    private String casting;
    private String synopsis;
    private String genre;
    private String ageRating;
    private String posterPath;
    private String videoPath;

    private List<View> views = new ArrayList<>();

    public Movie(String title, int releaseYear, int duration, String directing,
                 String casting, String synopsis, String genre, String ageRating,
                 String posterPath, String videoPath) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.directing = directing;
        this.casting = casting;
        this.synopsis = synopsis;
        this.genre = genre;
        this.ageRating = ageRating;
        this.posterPath = posterPath;
        this.videoPath = videoPath;
    }

    public Movie(UUID movieUUID, String title, int releaseYear, int duration, String directing,
                 String casting, String synopsis, String genre, String ageRating,
                 String posterPath, String videoPath) {
        this.movieUUID = movieUUID;
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.directing = directing;
        this.casting = casting;
        this.synopsis = synopsis;
        this.genre = genre;
        this.ageRating = ageRating;
        this.posterPath = posterPath;
        this.videoPath = videoPath;
    }

    public void addView(View view) {
        this.views.add(view);
        view.setMovie(this);
    }
}
