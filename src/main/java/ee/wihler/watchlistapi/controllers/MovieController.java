package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.dtos.ApiResponse;
import ee.wihler.watchlistapi.dtos.MovieDTO;
import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.services.MovieServiceImp;
import ee.wihler.watchlistapi.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// TODO implement ApiResponse for every endpoint
@RestController
@RequestMapping("/api/movies")
    public class MovieController {
        @Autowired
        private MovieServiceImp movieService;

        @Autowired
        private UserServiceImp userService;

        @GetMapping
        @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
        public List<MovieDTO> getAllMovies(@RequestHeader("Authorization") String token) {
            Integer userId = userService.getUserIdFromToken(token);
            List<Movie> movies = movieService.getAllMovies();

            return movies.stream().map(movie -> {
                MovieDTO movieDTO = new MovieDTO();
                movieDTO.setId(movie.getId());
                movieDTO.setTitle(movie.getTitle());
                movieDTO.setDescription(movie.getDescription());
                movieDTO.setReleaseDate(movie.getReleaseDate());
                movieDTO.setCreatedAt(movie.getCreatedAt());
                movieDTO.setInWatchlist(movieService.isMovieInWatchlist(movie.getId(), userId));
                return movieDTO;
            }).collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public Movie getMovieById(@PathVariable Integer id) {
            return movieService.getMovieById(id);
        }

        @PostMapping("/save")
        public ResponseEntity<ApiResponse<?>> saveMovie(@RequestBody Movie movie) {
            movieService.saveMovie(movie);

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .success(true)
                            .message("Movie saved successfully")
                            .data(movie)
                            .build()
            );
        }

        @PostMapping("/delete")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public void deleteMovie(@RequestBody Movie movie) {
            movieService.deleteMovie(movie);
        }

        @PostMapping("/update")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public void updateMovie(@RequestBody Movie movie) {
            movieService.updateMovie(movie);
        }
}
