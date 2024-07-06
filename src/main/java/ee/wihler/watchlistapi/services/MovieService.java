package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Integer id);
    List<Movie> getMoviesByIds(List<Integer> ids);
    void saveMovie(Movie movie);
    void deleteMovie(Integer id);
}
