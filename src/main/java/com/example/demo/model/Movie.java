package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="Movie")
@Table(name="MOVIE_TABLE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID")
    private long movieId;
    @Column(name = "MOVIE_TITLE")
    private String title;
    @Column(name = "MOVIE_RELEASING")
    private int releaseYear;
    @Column(name = "MOVIE_DURATION")
    private int duration;
    @Column(name = "MOVIE_DIRECTING")
    private String directing;
    @Column(name = "MOVIE_CASTING")
    private String casting;
    @Column(name = "MOVIE_SYNOPSIS", columnDefinition="TEXT")
    private String synopsis;
    @Column(name = "MOVIE_GENRE")
    private String genre;
    @Column(name = "MOVIE_AGE_RATING")
    private String ageRating;
    @Column(name = "POSTER_PATH")
    private String posterPath;
    @Column(name = "VIDEO_PATH")
    private String videoPath;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
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

    public void addView(View view) {
        this.views.add(view);
        view.setMovie(this);
    }
}
