package ee.wihler.watchlistapi.controllers;

import ee.wihler.watchlistapi.entities.User;
import ee.wihler.watchlistapi.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// TODO implement ApiResponse for every endpoint
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
