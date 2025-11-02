package com.example.oenskeskyen.repositories;

import com.example.oenskeskyen.models.User;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setName(rs.getString("username")); // matcher din kolonne i databasen
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password_hash")); // matcher din kolonne i databasen
        user.setWishlists(null);

        return user;
    }
}
