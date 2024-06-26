package ee.wihler.watchlistapi.repositories;

import ee.wihler.watchlistapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}