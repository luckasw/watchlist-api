package ee.wihler.watchlistapi.factories;

import ee.wihler.watchlistapi.entities.Role;
import ee.wihler.watchlistapi.enums.RoleEnum;
import ee.wihler.watchlistapi.exeption.RoleNotFoundException;
import ee.wihler.watchlistapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    @Autowired
    RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        switch (role) {
            case "admin" -> {
                return roleRepository.findByName(RoleEnum.ROLE_ADMIN);
            }
            case "user" -> {
                return roleRepository.findByName(RoleEnum.ROLE_USER);
            }
            default -> throw new RoleNotFoundException("Role not found for " + role);
        }
    }
}
