package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.dtos.WatchlistMovieDTO;
import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.entities.User;
import ee.wihler.watchlistapi.entities.WatchlistMovie;
import ee.wihler.watchlistapi.services.UserServiceImp;
import ee.wihler.watchlistapi.services.WatchlistServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO implement ApiResponse for every endpoint
@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    private static final Logger log = LoggerFactory.getLogger(WatchlistController.class);
    @Autowired
    private WatchlistServiceImp watchlistService;

    @Autowired
    private UserServiceImp userService;

    @GetMapping
    public List<WatchlistMovieDTO> getUserWatchlist(@RequestHeader("Authorization") String token) {
        log.debug("Getting user watchlist");
        return watchlistService.getAllUserWatchlist(userService.getUserIdFromToken(token));
    }

    @PostMapping("/update")
    public void addMovie(@RequestHeader("Authorization") String token, @RequestBody Movie movie) {
        User user = userService.getUserById(userService.getUserIdFromToken(token));

        WatchlistMovie watchlistMovie = new WatchlistMovie();
        watchlistMovie.setUser(user);
        watchlistMovie.setMovie(movie);

        WatchlistMovie watchlistMovieFromDb = watchlistService.getWatchlistByUserIdAndMovieId(user.getId(), movie.getId());
        if (watchlistMovieFromDb != null) {
            watchlistService.removeFromWatchlist(watchlistMovieFromDb.getId());
        } else {
            watchlistService.addToWatchlist(watchlistMovie);
        }
    }

    @PostMapping("/set-watched")
    public void setWatched(@RequestHeader("Authorization") String token, @RequestBody WatchlistMovie watchlistMovie) {
        log.debug("Setting watched: " + watchlistMovie);
        User user = userService.getUserById(userService.getUserIdFromToken(token));
        watchlistMovie.setUser(user);
        watchlistService.setWatched(watchlistMovie);
    }

}
