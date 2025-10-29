package service;

import models.Wish;
import org.springframework.stereotype.Service;
import repositories.WishRepository;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void addWishToWishlist(Wish wish, int wishlistId) {
        int wishId = wishRepository.saveWish(wish);
        if (wishId > 0) {
            wishRepository.linkWishToWishlist(wishId, wishlistId);
        }
    }
}
