package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Integer id);
    List<Movie> getMoviesByIds(List<Integer> ids);
    boolean isMovieInWatchlist(Integer movieId, Integer userId);
    void saveMovie(Movie movie);
    void deleteMovie(Movie movie);

    void updateMovie(Movie movie);
}
