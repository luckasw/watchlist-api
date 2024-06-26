package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Integer id);
    void saveMovie(Movie movie);
    void deleteMovie(Integer id);
}
