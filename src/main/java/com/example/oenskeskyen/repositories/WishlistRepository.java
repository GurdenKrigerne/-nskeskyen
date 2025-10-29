package com.example.oenskeskyen.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //getAllWishlists

    //findWishlistById

    //addWishlist

    //deleteWishList
    public boolean deleteWishlistById(int id) {
        String sql = "DELETE FROM Wishlist WHERE wishlist_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;

    }


    //editWishlist

    //addWishToWishlist
}

