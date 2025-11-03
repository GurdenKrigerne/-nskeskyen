package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.models.User;
import com.example.oenskeskyen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //getAllUsers
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers"; // henviser til allUsers.html
    }


    //findUserById
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") int userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "findUserById"; // user-detail.html i templates
    }

}
