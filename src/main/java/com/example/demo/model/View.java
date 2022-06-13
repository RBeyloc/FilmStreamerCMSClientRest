package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="View")
@Table(name="VIEW_TABLE")
public class View {

    @Id
    @GeneratedValue (generator = "uuid2")
    @GenericGenerator (name = "uuid2", strategy = "uuid2")
    @Column (name = "VIEW_UUID", columnDefinition = "BINARY (16)")
    private UUID viewUUID;
    @Column(name = "VIEW_DATETIME")
    private LocalDateTime viewDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_FK", referencedColumnName="USER_UUID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="MOVIE_FK", referencedColumnName="MOVIE_UUID")
    private Movie movie;

    public View(User user, Movie movie) {
        this.viewDateTime = LocalDateTime.now();
        this.user = user;
        this.movie = movie;
    }

}
