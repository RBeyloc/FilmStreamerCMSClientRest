package com.example.demo.model;

import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class User implements Serializable {

    private UUID userUUID;
    private String userEmail;
    private String password;
    private String userRol;
    private String userFirstName;
    private String userLastName;
    private String birthDate;

    private List<View> views = new ArrayList<>();

    public User(String userEmail, String password, String userRol,
                String userFirstName, String userLastName, String birthDate) {
        this.userEmail = userEmail;
        this.password = password;
        this.userRol = userRol;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.birthDate = birthDate.toString();
    }

    public User(UUID uuid, String userEmail, String password, String userRol,
                String userFirstName, String userLastName, String birthDate) {
        this.userUUID = uuid;
        this.userEmail = userEmail;
        this.password = password;
        this.userRol = userRol;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.birthDate = birthDate.toString();
    }

    public void addView(View view) {
        this.views.add(view);
        view.setUser(this);
    }

}
