package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.User;

public interface UserService {
    void saveUser(User user);
    void deleteUser(Integer id);
    User getUserById(Integer id);
    Integer getUserIdFromToken(String token);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
