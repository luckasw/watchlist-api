package ee.wihler.watchlistapi.dtos;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class MovieDTO {
    private Integer id;
    private String title;
    private String Description;
    private LocalDate releaseDate;
    private Instant createdAt;
    private boolean isInWatchlist;
}
