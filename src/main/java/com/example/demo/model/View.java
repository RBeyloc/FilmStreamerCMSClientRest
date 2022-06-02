package com.example.demo.model;

import com.example.demo.model.Movie;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="View")
@Table(name="VIEW_TABLE")
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VIEW_ID")
    private long viewId;
    @Column(name = "VIEW_DATETIME")
    private LocalDateTime viewDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_FK")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="MOVIE_FK")
    private Movie movie;

    public View(User user, Movie movie) {
        this.viewDateTime = LocalDateTime.now();
        this.user = user;
        this.movie = movie;
    }

}
