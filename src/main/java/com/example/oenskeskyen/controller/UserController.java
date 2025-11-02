package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.models.User;
import com.example.oenskeskyen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //add user
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    //edit user
    @PutMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/users";
    }

    //delete user
    @DeleteMapping("/delete")
    public String deleteUserById(@RequestParam int userId) {
        userService.deleteUserById(userId);
        return "redirect:/users";
    }


}
