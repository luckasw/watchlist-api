package ee.wihler.watchlistapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, message="Username must be at least 3 characters long")
    @Size(max = 20, message="Username must be at most 20 characters long")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email is not in valid format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Size(max = 30, message = "Password must be at most 30 characters long")
    private String password;

    private Set<String> roles;

}
