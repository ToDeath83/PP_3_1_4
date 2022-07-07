package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;


@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model, Principal principal) {
        model.addAttribute("roles", userService.listRoles());
        model.addAttribute("userAdmin", userService.findByUsername(principal.getName()));
        return "admin";
    }

//    @GetMapping("/admin/adminpage")
//    public String users(ModelMap model, Principal principal) {
//        model.addAttribute("users", userService.getUsers());
//        model.addAttribute("roles", userService.listRoles());
//        model.addAttribute("userAdmin", userService.findByUsername(principal.getName()));
//        return "admin";
//    }
//
//    @PostMapping("/admin/adminpage/actionsWithUser")
//    public String update(@ModelAttribute("user") User user, @RequestParam("listRoles") long[] role_id) {
//        userService.saveUser(user, role_id);
//        return "redirect:/admin/adminpage";
//    }
//
//    @PostMapping("/admin/adminpage/delete/{id}")
//    public String removeUser(@PathVariable Long id) {
//        userService.deleteUser(userService.readUser(id));
//        return "redirect:/admin/adminpage";
//    }
}
