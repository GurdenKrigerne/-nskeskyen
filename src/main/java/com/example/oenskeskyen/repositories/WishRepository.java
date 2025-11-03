package com.example.oenskeskyen.repositories;

import com.example.oenskeskyen.models.Wish;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //getAllWishes
    public List<Wish> getAllWishes() {
        String sql = "SELECT * FROM oenskeskyen.Wish";
        return jdbcTemplate.query(sql, new WishRowMapper());
    }

    //findWishById
    public Wish findWishById(int wishId) {
        String sql = "SELECT * FROM Wish WHERE wish_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishRowMapper(), wishId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    //deleteWish
    public boolean deleteWishById(int id) {
        String sql = "DELETE FROM Wish WHERE wish_id = ?"; // Fjerner ønsket med det specifikke id
        int rowsAffected = jdbcTemplate.update(sql, id); //returnerer antallet af slettede rækker
        return rowsAffected > 0; // True = slettet, false = ikke fundet
    }

    // Opdaterer et eksisterende ønske
    public boolean editWish(Wish wish) {
        String sql = "UPDATE Wish SET title = ?, price = ?, description = ? WHERE wish_id = ?";
        int rowsAffected = jdbcTemplate.update(
                sql,
                wish.getTitle(),
                wish.getPrice(),
                wish.getDescription(),
                wish.getWishId()
        );
        return rowsAffected > 0; // True = opdateret, false = ikke fundet
    }

    //addWish
    public void saveWish(Wish wish) {
        String sql = "INSERT INTO Wish (title, description, price, url) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wish.getTitle(), wish.getDescription(), wish.getPrice(), wish.getUrl());

    }

    public void linkWishToWishlist(int wishId, int wishlistId) {
        String sql = "INSERT INTO Wishlist_Wish (wishlist_id, wish_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishlistId, wishId);
    }





}
