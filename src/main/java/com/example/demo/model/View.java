package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class View implements Serializable {

    private UUID viewUUID;
    private LocalDateTime viewDateTime;
    private User user;
    private Movie movie;

    public View(User user, Movie movie) {
        this.viewDateTime = LocalDateTime.now();
        this.user = user;
        this.movie = movie;
    }

}
