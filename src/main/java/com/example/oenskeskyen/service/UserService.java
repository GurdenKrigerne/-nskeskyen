package com.example.oenskeskyen.service;

import com.example.oenskeskyen.models.User;
import com.example.oenskeskyen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


}
