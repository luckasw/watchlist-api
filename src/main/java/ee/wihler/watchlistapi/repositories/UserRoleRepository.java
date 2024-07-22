package ee.wihler.watchlistapi.repositories;

import ee.wihler.watchlistapi.entities.UserRole;
import ee.wihler.watchlistapi.entities.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}