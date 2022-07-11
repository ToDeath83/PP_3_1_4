package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RestAdminController {

    private final UserService userService;

    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminpage")
    public List<User> userList() {
        return userService.getUsers();
    }

    @GetMapping("/adminpage/getUser/{id}")
    public User getUser(@PathVariable Long id){
        return userService.readUser(id);
    }

    @PostMapping("/adminpage/new")
    public List<User> addUser(@RequestBody @Valid User user, @RequestParam("listRoles") long[] role_id) {
        System.out.println(user);
        userService.saveUser(user, role_id);
        return userService.getUsers();
    }

    @PutMapping("/adminpage/edit")
    public ResponseEntity<?> update(@RequestBody @Valid User user, @RequestParam("listRoles") long[] role_id) {
        System.out.println(user);
        userService.saveUser(user, role_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/adminpage/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
        userService.deleteUser(userService.readUser(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
