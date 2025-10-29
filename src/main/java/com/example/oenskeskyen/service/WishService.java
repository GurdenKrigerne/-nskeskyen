package com.example.oenskeskyen.service;

import com.example.oenskeskyen.models.Wish;
import org.springframework.stereotype.Service;
import com.example.oenskeskyen.repositories.WishRepository;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
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

}
