package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.dtos.ApiResponse;
import ee.wihler.watchlistapi.dtos.SignInRequest;
import ee.wihler.watchlistapi.dtos.SignInResponse;
import ee.wihler.watchlistapi.dtos.SignUpRequest;
import ee.wihler.watchlistapi.entities.Role;
import ee.wihler.watchlistapi.entities.User;
import ee.wihler.watchlistapi.exeption.RoleNotFoundException;
import ee.wihler.watchlistapi.exeption.UserAlreadyExistsException;
import ee.wihler.watchlistapi.factories.RoleFactory;
import ee.wihler.watchlistapi.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleFactory roleFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResponseEntity<ApiResponse<?>> signUp(SignUpRequest signUpRequest) throws UserAlreadyExistsException, RoleNotFoundException {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + signUpRequest.getEmail() + " already exists");
        }
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + signUpRequest.getUsername() + " already exists");
        }

        User user = createUser(signUpRequest);
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .success(true)
                        .message("User registered successfully")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<?>> signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        SignInResponse signInResponse = SignInResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Sign in successful")
                        .data(signInResponse)
                        .build()
        );
    }

    private User createUser(SignUpRequest signUpRequest) throws RoleNotFoundException {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = determineRoles(signUpRequest.getRoles());
        user.setRoles(roles);

        return user;
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if(strRoles == null) {
            roles.add((roleFactory.getInstance("user")));
        } else {
            for (String role : strRoles) {
                roles.add(roleFactory.getInstance(role));
            }
        }

        return roles;
    }
}
