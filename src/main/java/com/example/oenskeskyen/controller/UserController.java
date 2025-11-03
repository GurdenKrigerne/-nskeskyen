package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.models.User;
import com.example.oenskeskyen.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // Forside med login og register forms
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }


    // Login
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {

        if (userService.login(email, password)) {
            User user = userService.getUserByEmail(email);
            session.setAttribute("user", user); // gem bruger i session
            return "redirect:/wishlists"; // ønskeliste-side
        } else {
            model.addAttribute("loginError", "Forkert email eller kodeord");
            return "home";
        }
    }

    // Opret bruger
    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {

        if (userService.getUserByEmail(email) != null) {
            model.addAttribute("registerError", "Email findes allerede");
            return "home";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userService.register(user);
        model.addAttribute("registerSuccess", "Bruger oprettet! Log ind nu.");
        return "home";
    }
}



