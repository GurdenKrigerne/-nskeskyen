package com.example.oenskeskyen.service;

import com.example.oenskeskyen.models.User;
import com.example.oenskeskyen.repositories.UserRepository;
import com.example.oenskeskyen.repositories.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //getAllUsers
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


    //findUserById
    public User findUserById(int userId) {
        return userRepository.findUserById(userId);
    }

    //add user
    public int addUser(User user) {
        return userRepository.addUser(user);
    }

    //edituser
    public boolean editUser(User user) {
        return userRepository.editUser(user);
    }

    //delete user
    public boolean deleteUser(int userId) {
        return userRepository.deleteUserById(userId);
    }

    // Find bruger efter email
    public User findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try {
            return userRepository.getJdbcTemplate().queryForObject(sql, new UserRowMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Login-metode
    public boolean login(String email, String password) {
        User user = findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    // Opret bruger
    public boolean register(User user) {
        if (findByEmail(user.getEmail()) != null) {
            return false; // email findes allerede
        }
        userRepository.addUser(user);
        return true;
    }

}
