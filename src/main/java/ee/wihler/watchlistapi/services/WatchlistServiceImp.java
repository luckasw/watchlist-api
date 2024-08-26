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
    public List<WatchlistMovieDTO> getAllUserWatchlist(Integer userId) {
        List<WatchlistMovie> watchlistMovies = watchlistRepository.findByUserId(userId);
        return watchlistMovies.stream()
                .map(watchlistMovie -> new WatchlistMovieDTO(
                        watchlistMovie.getId(),
                        watchlistMovie.getMovie(),
                        watchlistMovie.getWatched(),
                        watchlistMovie.getWatchDate(),
                        watchlistMovie.getRating(),
                        watchlistMovie.getReview(),
                        watchlistMovie.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void addToWatchlist(WatchlistMovie watchlistMovie) {
        watchlistRepository.save(watchlistMovie);
    }

    @Override
    public void removeFromWatchlist(Integer watchlistId) {
        watchlistRepository.deleteById(watchlistId);
    }

    @Override
    public WatchlistMovie getWatchlistByUserIdAndMovieId(Integer userId, Integer movieId) {
        return watchlistRepository.findByUserIdAndMovieId(userId, movieId);
    }
}
