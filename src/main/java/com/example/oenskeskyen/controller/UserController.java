package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.models.User;
import com.example.oenskeskyen.service.UserService;
import jakarta.servlet.http.HttpSession;
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

    //editUser
    @GetMapping("users/edit/{id}")
    public String showEditUser(@PathVariable int id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "editUser"; // editUser.html
    }

    @PostMapping("users/edit")
    public String editUser(@ModelAttribute User user) {

        boolean success = userService.editUser(user);

        if (success) {
            return "redirect:/users";
        } else {
            return "error"; // kan være en error page eller redirect til edit med message
        }
    }

    //add user
    // GET: vis add user form
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User()); // tom bruger til formen
        return "addUser"; // addUser.html
    }

    // POST: modtag form og gem user
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        int rows = userService.addUser(user); // kalder service/repository

        if (rows > 0) {
            return "redirect:/users"; // tilbage til user list hvis succes
        } else {
            return "error"; // alternativ error side
        }
    }

    //delete user
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int userId) {
        boolean success = userService.deleteUser(userId);

        if (success) {
            return "redirect:/users"; // tilbage til users liste hvis succes
        } else {
            return "error"; // alternativ error side
        }
    }



    @GetMapping("/")
    public String showHomePage() {
        return "home"; // Thymeleaf template med login og register forms
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {

        if (userService.login(email, password)) {
            User user = userService.findByEmail(email);
            session.setAttribute("user", user);
            return "redirect:/wishlists"; // side med brugerens ønskelister
        } else {
            model.addAttribute("loginError", "Forkert email eller kodeord");
            return "home";
        }
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        if (!userService.register(user)) {
            model.addAttribute("registerError", "Email findes allerede");
            return "home";
        }

        model.addAttribute("registerSuccess", "Bruger oprettet! Log ind nu.");
        return "home";
    }
}


