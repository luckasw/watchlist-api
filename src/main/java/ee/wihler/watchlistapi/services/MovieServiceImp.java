package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.Movie;
import ee.wihler.watchlistapi.repositories.MovieRepository;
import ee.wihler.watchlistapi.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Integer id) {
        Optional<Movie> optional = movieRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Movie> getMoviesByIds(List<Integer> ids) {
        return movieRepository.findAllById(ids);
    }

    @Override
    public boolean isMovieInWatchlist(Integer movieId, Integer userId) {
        return watchlistRepository.existsByUserIdAndMovieId(userId, movieId);
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.deleteById(movie.getId());
    }

    @Override
    public void updateMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
        if (existingMovie.isPresent()) {
            Movie updatedMovie = existingMovie.get();
            updatedMovie.setTitle(movie.getTitle());
            updatedMovie.setDescription(movie.getDescription());
            updatedMovie.setReleaseDate(movie.getReleaseDate());
            movieRepository.save(updatedMovie);
        } else {
            // TODO throw exception
        }
    }
}
