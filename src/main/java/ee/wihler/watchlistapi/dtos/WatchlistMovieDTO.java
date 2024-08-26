package ee.wihler.watchlistapi.dtos;

import ee.wihler.watchlistapi.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WatchlistMovieDTO {
    private Integer id;
    private Movie movie;
    private Boolean watched;
    private LocalDate watchDate;
    private Integer rating;
    private String review;
    private Instant createdAt;
}
