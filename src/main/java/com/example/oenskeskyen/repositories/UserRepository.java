package com.example.oenskeskyen.repositories;

import com.example.oenskeskyen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    //getAllUsers
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
    //findUserById
    public User findUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

    public int addUser(User user) {
        String sql = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    null // vi henter ikke wishlists her
            ), email);
        } catch (EmptyResultDataAccessException e) {
            return null; // return null hvis brugeren ikke findes
        }
    }

    public void save(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
    }
}



    //editUser
    public boolean editUser(User user) {
        String sql = "UPDATE Users SET email = ?, password = ? WHERE user_id = ?";
        int rows = jdbcTemplate.update(sql,
                user.getEmail(),
                user.getPassword(),
                user.getUserId()
        );
        return rows > 0;
    }
    //deleteUser
    public boolean deleteUserById(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        int rows = jdbcTemplate.update(sql, userId);
        return rows > 0;
    }

