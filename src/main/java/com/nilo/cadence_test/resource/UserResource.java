package com.nilo.cadence_test.resource;

import com.nilo.cadence_test.model.User;
import com.nilo.cadence_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserResource {
    @Autowired
    UserService userService;

    @GetMapping(value = "/all")
    public List<User> getAll() {
        return null;
    }

    @PostMapping(value = "/save")
    public User persist(@RequestBody User user) {
        return userService.save(user);
    }
}
