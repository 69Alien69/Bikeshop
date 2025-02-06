package ru.hackass122.bikeshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hackass122.bikeshop.model.User;
import ru.hackass122.bikeshop.service.RoleService;
import ru.hackass122.bikeshop.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.saveUser(user);
        return "redirect:/login.html";
    }
}
