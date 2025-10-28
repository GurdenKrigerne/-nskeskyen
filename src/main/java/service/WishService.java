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

    // FindWishById - Finde et specifikt ønske
    public Wish findWishById(int id) {
        return wishRepository.findWishById(id);
    }

    //DeleteWishById
    public boolean deleteWish(int id) {
        return wishRepository.deleteWishById(id);
    }
}
