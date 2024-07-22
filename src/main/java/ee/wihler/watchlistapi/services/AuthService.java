package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.dtos.ApiResponse;
import ee.wihler.watchlistapi.dtos.SignInRequest;
import ee.wihler.watchlistapi.dtos.SignUpRequest;
import ee.wihler.watchlistapi.exeption.RoleNotFoundException;
import ee.wihler.watchlistapi.exeption.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<ApiResponse<?>> signUp(SignUpRequest signUpRequest) throws UserAlreadyExistsException, RoleNotFoundException;
    ResponseEntity<ApiResponse<?>> signIn(SignInRequest signInRequest);
}
