package com.example.oenskeskyen.service;

import com.example.oenskeskyen.models.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.oenskeskyen.repositories.WishRepository;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;
    private final JdbcTemplate jdbcTemplate;

    public WishService(WishRepository wishRepository, JdbcTemplate jdbcTemplate) {
        this.wishRepository = wishRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    // findWishById - Finde et specifikt ønske
    public Wish findWishById(int id) {
        return wishRepository.findWishById(id);
    }

    //deleteWishById
    public boolean deleteWish(int id) {
        return wishRepository.deleteWishById(id);
    }

    //editWish
    public boolean editWish(Wish wish) {
        return wishRepository.editWish(wish);
    }

    public void addWishToWishlist(Wish wish, int wishlistId) {
        System.out.println("Gemmer nyt wish i databasen...");
        wishRepository.saveWish(wish);

        // Hent sidste indsatte ID
        int wishId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        System.out.println("Ny wish_id = " + wishId + ", linker til wishlist_id = " + wishlistId);

        wishRepository.linkWishToWishlist(wishId, wishlistId);
    }
    public List<Wish> getAllWishes() { return wishRepository.getAllWishes(); }


    public List<Wish> getWishesByWishlistId(int wishlistId) {
        return wishRepository.getWishesByWishlistId(wishlistId);
    }

    public int getWishlistIdByWishId(int wishId) {
        return wishRepository.getWishlistIdByWishId(wishId);
    }

}
