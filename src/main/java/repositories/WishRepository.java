package repositories;

import models.Wish;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //getAllWishes


    //findWishById
    public Wish findWishById(int wishId) {
        String sql = "SELECT * FROM wishes WHERE wishId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishRowMapper(), wishId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    //addWish

    //deleteWish
    public boolean deleteWishById(int id) {
        String sql = "DELETE FROM wishes WHERE id = ?"; // Fjerner ønsket med det specifikke id
        int rowsAffected = jdbcTemplate.update(sql, id); //returnerer antallet af slettede rækker
        return rowsAffected > 0; // True = slettet, false = ikke fundet
    }

    //editWish



}
