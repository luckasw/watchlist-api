package ee.wihler.watchlistapi.repositories;

import ee.wihler.watchlistapi.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}