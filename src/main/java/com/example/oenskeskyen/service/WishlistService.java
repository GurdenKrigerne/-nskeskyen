package com.example.oenskeskyen.service;

import com.example.oenskeskyen.models.WishList;
import com.example.oenskeskyen.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }


    //findWishlistById
    public WishList findWishlistById(int id) {
        return wishlistRepository.findWishlistById(id);
    }

    //deleteWishlist
    public boolean deleteWishlist(int id) {
        return wishlistRepository.deleteWishlistById(id);
    }

    //editWishlist
    public boolean editWishlist(WishList wishlist) {
        return wishlistRepository.editWishlist(wishlist);
    }

    public void createWishlist(WishList wishlist) {
        wishlistRepository.createWishlist(wishlist);
    }

}