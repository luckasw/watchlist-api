package ee.wihler.watchlistapi.dtos;

import lombok.Getter;

@Getter
public class AddWatchlistRequest {
    private Integer userId;
    private Integer movieId;
}
