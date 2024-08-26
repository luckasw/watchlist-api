package ee.wihler.watchlistapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "watchlist_movies")
public class WatchlistMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('watchlist_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "created_at", insertable = false)
    private Instant createdAt;

    @Column(name = "watched", insertable = false)
    private Boolean watched;

    @Column(name = "watch_date")
    private LocalDate watchDate;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review", length = Integer.MAX_VALUE)
    private String review;

}