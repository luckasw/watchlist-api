package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.entities.Watchlist;
import ee.wihler.watchlistapi.repositories.WatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class WatchlistServiceImp implements WatchlistService {
    private static final Logger log = LoggerFactory.getLogger(WatchlistServiceImp.class);
    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private MovieServiceImp movieService;

    @Override
    public List<Movie> getAllUserMovieId(Integer userId) {
        log.debug("Getting all movie ids for user: " + userId);
        List<Integer> movieIds = watchlistRepository.findAllByUserId(userId);
        log.debug("Movie ids found: " + movieIds.toString());
        return movieService.getMoviesByIds(movieIds);
    }

    @Override
    public void addToWatchlist(Watchlist watchlist) {
        watchlistRepository.save(watchlist);
    }

    @Override
    public void removeFromWatchlist(Integer watchlistId) {
        watchlistRepository.deleteById(watchlistId);
    }

    @Override
    public Watchlist getWatchlistByUserIdAndMovieId(Integer userId, Integer movieId) {
        return watchlistRepository.findByUserIdAndMovieId(userId, movieId);
    }
}
