package com.example.demo.model;

import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class User {

    private UUID userUUID;
    private String userEmail;
    private int password;
    private String userRol;
    private String userFirstName;
    private String userLastName;
    private LocalDate birthDate;

    private List<View> views = new ArrayList<>();

    public User(String userEmail, int password, String userRol,
                String userFirstName, String userLastName, LocalDate birthDate) {
        this.userEmail = userEmail;
        this.password = password;
        this.userRol = userRol;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.birthDate = birthDate;
    }

    public void addView(View view) {
        this.views.add(view);
        view.setUser(this);
    }

}
