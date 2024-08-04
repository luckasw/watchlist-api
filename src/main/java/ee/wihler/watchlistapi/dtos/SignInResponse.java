package ee.wihler.watchlistapi.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignInResponse {
    private String token;
    private String username;
    private String email;
    private List<String> roles;
}
