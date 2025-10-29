package com.example.oenskeskyen.repositories;

import com.example.oenskeskyen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //getAllUsers
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM oenskeskyen.Users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
    //findById
    public User findById(int userId) {
        String sql = "SELECT * FROM oenskeskyen.Users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

    public int addUser(User user) {
        String sql = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
    }


    //editUser

    //deleteUser


}
