package ee.wihler.watchlistapi.repositories;

import ee.wihler.watchlistapi.entities.Role;
import ee.wihler.watchlistapi.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleEnum name);
}