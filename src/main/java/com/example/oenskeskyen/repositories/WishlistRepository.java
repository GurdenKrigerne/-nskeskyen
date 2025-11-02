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
    public List<WishList> getAllWishlists() {
        String sql = "SELECT * FROM Wishlist";
        return jdbcTemplate.query(sql, new WishlistRowMapper());
    }

    //findWishlistById
    public WishList findWishlistById(int wishlistId) {
        String sql = "SELECT * FROM Wishlist WHERE wishlist_id = ?";
        return jdbcTemplate.queryForObject(sql, new WishlistRowMapper(), wishlistId);
    }

    //addWishlist
    public void createWishlist(WishList wishlist) {
        String sql = "INSERT INTO Wishlist (name, description, owner_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, wishlist.getTitle(), wishlist.getDescription(), wishlist.getUserId());
    }

    //deleteWishList
    public boolean deleteWishlistById(int id) {
        String sql = "DELETE FROM Wishlist WHERE wishlist_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;

    }


    //editWishlist
    public boolean editWishlist(WishList wishlist) {
        String sql = "UPDATE Wishlist SET name = ?, description = ? WHERE wishlist_id = ?";
        int rows = jdbcTemplate.update(sql,
                wishlist.getTitle(),
                wishlist.getDescription(),
                wishlist.getWishlistId());
        return rows > 0;
    }

    //addWishToWishlist
    public int addWishToWishlist(int wishlistId, int wishId) {
        String sql = "INSERT INTO Wishlist_Wish (wishlist_id, wish_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, wishlistId, wishId);
    }
}

    //addWishToWishlist


