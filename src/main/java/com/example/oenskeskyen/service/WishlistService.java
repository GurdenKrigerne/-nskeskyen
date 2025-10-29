package com.example.oenskeskyen.service;

import com.example.oenskeskyen.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }


    //deleteWishlist
    public boolean deleteWishlist(int id) {
        return wishlistRepository.deleteWishlistById(id);
    }

}