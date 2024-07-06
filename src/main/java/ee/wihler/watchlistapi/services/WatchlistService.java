package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.entities.Watchlist;

import java.util.List;

public interface WatchlistService {
    List<Movie> getAllUserMovieId(Integer userId);
    void addMovie(Watchlist watchlist);
    void deleteMovie(Integer id);
}
