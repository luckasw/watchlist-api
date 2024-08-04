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
    public List<Movie> getAllUserMovieId(@RequestHeader("Authorization") String token) {
        return watchlistService.getAllUserMovieId(userService.getUserIdFromToken(token));
    }

    @PostMapping("/update")
    public void addMovie(@RequestHeader("Authorization") String token, @RequestBody Movie movie) {
        User user = userService.getUserById(userService.getUserIdFromToken(token));

        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setMovie(movie);

        Watchlist watchlistFromDb = watchlistService.getWatchlistByUserIdAndMovieId(user.getId(), movie.getId());
        if (watchlistFromDb != null) {
            watchlistService.removeFromWatchlist(watchlistFromDb.getId());
        } else {
            watchlistService.addToWatchlist(watchlist);
        }
    }

}
