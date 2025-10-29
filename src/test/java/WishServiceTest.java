import models.Wish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.WishRepository;
import service.WishService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WishServiceTest {

    private WishRepository wishRepository;
    private WishService wishService;

    @BeforeEach
    void setUp() {
        wishRepository = Mockito.mock(WishRepository.class);
        wishService = new WishService(wishRepository);
    }


    @Test
    void addWish_ShouldCallRepository_WhenWishIsValid() {
        Wish wish = new Wish();
        wish.setTitle("Playstation");
        wish.setDescription("Spillekonsol");
        wish.setPrice(300.0);
        wish.setId(1);
        wish.setWishListId(3);

        wishService.addWishToWishlist(wish, wish.getWishListId());

        verify(wishRepository, times(1)).saveWish(wish);
    }


    @Test
    void addWish_ShouldThrowException_WhenNameIsEmpty() {
        Wish wish = new Wish();
        wish.setTitle(" ");
        wish.setWishListId(1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wishService.addWishToWishlist(wish, wish.getWishListId());
        });

        assertEquals("Wish name cannot be empty", exception.getMessage());
        verify(wishRepository, never()).saveWish(any());
    }
}
