package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.User;
import ee.wihler.watchlistapi.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ee.wihler.watchlistapi.security.jwt.JwtUtils;

@Service
public class UserServiceImp implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Integer getUserIdFromToken(String token) {
        log.debug("Getting username from token");
        String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
        log.debug("Username from token: " + username);
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        } else {
            return user.getId();
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
