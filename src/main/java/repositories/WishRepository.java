package repositories;

import models.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //getAllWishes

    //findWishById

    //addWish
    public int saveWish(Wish wish) {
        String sql = "INSERT INTO Wish (title, description, price, url) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wish.getTitle(), wish.getDescription(), wish.getPrice(), wish.getUrl());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        return id;
    }

    public void linkWishToWishlist(int wishId, int wishlistId) {
        String sql = "INSERT INTO Wishlist_Wish (wishlist_id, wish_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishId, wishId);
    }
    //deleteWish

    //editWish


}
