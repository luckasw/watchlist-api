package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.dto.AddWatchlistRequest;
import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.entities.User;
import ee.wihler.watchlistapi.entities.Watchlist;
import ee.wihler.watchlistapi.services.MovieServiceImp;
import ee.wihler.watchlistapi.services.UserServiceImp;
import ee.wihler.watchlistapi.services.WatchlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    @Autowired
    private WatchlistServiceImp watchlistService;

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private MovieServiceImp movieService;

    @RequestMapping("/{userId}")
    public List<Movie> getAllUserMovieId(@PathVariable Integer userId) {
        return watchlistService.getAllUserMovieId(userId);
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody AddWatchlistRequest request) {
        User user = userService.getUserById(request.getUserId());
        Movie movie = movieService.getMovieById(request.getMovieId());

        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setMovie(movie);

        watchlistService.addMovie(watchlist);
    }

    @GetMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        watchlistService.deleteMovie(id);
    }

}
