package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.dtos.ApiResponse;
import ee.wihler.watchlistapi.dtos.SignInRequest;
import ee.wihler.watchlistapi.dtos.SignUpRequest;
import ee.wihler.watchlistapi.exeption.RoleNotFoundException;
import ee.wihler.watchlistapi.exeption.UserAlreadyExistsException;
import ee.wihler.watchlistapi.services.AuthService;
import ee.wihler.watchlistapi.services.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody @Valid SignUpRequest signUpRequest) throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> singInUser(@RequestBody @Valid SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }
}
