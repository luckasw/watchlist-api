package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.services.MovieServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieServiceImp movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/save")
    public void saveMovie(@RequestBody Movie movie) {
        movieService.saveMovie(movie);
    }

    @GetMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }

}
