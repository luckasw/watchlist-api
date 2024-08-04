package ee.wihler.watchlistapi.repositories;

import ee.wihler.watchlistapi.entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
    @Query("SELECT w.movie.id FROM Watchlist w WHERE w.user.id = ?1")
    List<Integer> findAllByUserId(Integer userId);

    boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);
    Watchlist findByUserIdAndMovieId(Integer userId, Integer movieId);
}