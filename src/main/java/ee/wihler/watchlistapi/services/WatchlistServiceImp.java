package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.Watchlist;
import ee.wihler.watchlistapi.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistServiceImp implements WatchlistService {
    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    public List<Integer> getAllUserMovieId(Integer userId) {
        return watchlistRepository.findAllByUserId(userId);
    }

    @Override
    public void addMovie(Watchlist watchlist) {

    }

    @Override
    public void deleteMovie(Integer id) {

    }
}
