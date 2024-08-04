package ee.wihler.watchlistapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('movies_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private Set<Watchlist> watchlists = new LinkedHashSet<>();

}