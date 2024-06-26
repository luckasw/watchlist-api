package ee.wihler.watchlistapi.repositories;

import ee.wihler.watchlistapi.entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
    @Query("SELECT w.movie FROM Watchlist w WHERE w.user = ?1")
    List<Integer> findAllByUserId(Integer userId);
}