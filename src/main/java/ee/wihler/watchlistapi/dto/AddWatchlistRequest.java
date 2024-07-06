package ee.wihler.watchlistapi.dto;

import lombok.Getter;

@Getter
public class AddWatchlistRequest {
    private Integer userId;
    private Integer movieId;
}
