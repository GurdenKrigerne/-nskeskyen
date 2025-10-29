package com.example.oenskeskyen.repositories;

import com.example.oenskeskyen.models.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //getAllWishlists
    public List<WishList> getAllWishlist() {
        String sql = "SELECT * FROM oenskeskyen.Wishlist";
        return jdbcTemplate.query(sql, new WishlistRowMapper());
    }

    //findWishlistById
    public WishList getWishListById(int wishlistId) {
        String sql = "SELECT * FROM oenskeskyen.Wishlist WHERE wishlist_id = ?";
        return jdbcTemplate.queryForObject(sql, new WishlistRowMapper(), wishlistId);
    }
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

