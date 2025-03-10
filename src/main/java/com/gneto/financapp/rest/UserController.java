package com.gneto.financapp.rest;

import com.gneto.financapp.entity.User;
import com.gneto.financapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financapp/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable("userId") String id) {
        return userService.findById(id);
    }

    @PostMapping
    public User add(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") String id) {
        userService.deleteById(id);
    }

}
