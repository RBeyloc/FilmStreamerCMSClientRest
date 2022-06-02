package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="User")
@Table(name="USER_TABLE")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @JsonIgnore
    @Column(name = "USER_PASSWORD")
    private int password;
    @Column(name = "USER_ROL")
    private String userRol;
    @Column(name = "USER_FIRSTNAME")
    private String userFirstName;
    @Column(name = "USER_LASTNAME")
    private String userLastName;
    @Column(name = "USER_BIRTHDATE")
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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
