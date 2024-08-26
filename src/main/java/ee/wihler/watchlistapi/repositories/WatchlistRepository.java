package ee.wihler.watchlistapi.repositories;

import ee.wihler.watchlistapi.entities.WatchlistMovie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<WatchlistMovie, Integer> {
    @EntityGraph(attributePaths = {"movie"})
    List<WatchlistMovie> findByUserId(Integer userId);

    boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);
    WatchlistMovie findByUserIdAndMovieId(Integer userId, Integer movieId);
}