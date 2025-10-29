package com.example.oenskeskyen.repositories;

import com.example.oenskeskyen.models.Wish;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {
    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wish wish = new Wish();

        wish.setWishId(rs.getInt("wish_id"));
        wish.setDescription(rs.getString("description"));
        wish.setPrice(rs.getDouble("price"));
        wish.setTitle(rs.getString("title"));

        return wish;
    }

}
