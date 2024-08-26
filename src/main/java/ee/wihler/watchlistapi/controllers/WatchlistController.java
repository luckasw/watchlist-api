package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.entities.User;
import ee.wihler.watchlistapi.entities.Watchlist;
import ee.wihler.watchlistapi.services.MovieServiceImp;
import ee.wihler.watchlistapi.services.UserServiceImp;
import ee.wihler.watchlistapi.services.WatchlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO implement ApiResponse for every endpoint
@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
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

}
