package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.dtos.WatchlistMovieDTO;
import ee.wihler.watchlistapi.entities.WatchlistMovie;

import java.util.List;

public interface WatchlistService {
    List<WatchlistMovieDTO> getAllUserWatchlist(Integer userId);

    void addToWatchlist(WatchlistMovie watchlistMovie);
    void removeFromWatchlist(Integer id);

    WatchlistMovie getWatchlistByUserIdAndMovieId(Integer userId, Integer movieId);

    void setWatched(WatchlistMovie watchlistMovie);
}
