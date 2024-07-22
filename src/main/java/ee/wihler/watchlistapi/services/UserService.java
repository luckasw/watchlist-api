package ee.wihler.watchlistapi.services;

import ee.wihler.watchlistapi.entities.User;

public interface UserService {
    void saveUser(User user);
    void deleteUser(Integer id);
    User getUserById(Integer id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
