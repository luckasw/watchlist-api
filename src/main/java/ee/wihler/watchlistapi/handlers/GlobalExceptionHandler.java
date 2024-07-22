package ee.wihler.watchlistapi.handlers;

import ee.wihler.watchlistapi.dtos.ApiResponse;
import ee.wihler.watchlistapi.exeption.RoleNotFoundException;
import ee.wihler.watchlistapi.exeption.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<String> errorMessage = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMessage.add(fieldError.getDefaultMessage());
        });

        return ResponseEntity
                .badRequest()
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message("Validation failed")
                                .data(errorMessage)
                                .build()
                );
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> RoleNotFoundExceptionHandler(RoleNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(e.getMessage())
                                .build()
                );
    }
}
