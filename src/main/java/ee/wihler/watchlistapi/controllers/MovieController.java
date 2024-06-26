package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.services.MovieServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieServiceImp movieService;

    @GetMapping
    public String getAllMovies() {
        return "Hello, World!";
    }

}
