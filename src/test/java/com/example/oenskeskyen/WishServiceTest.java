package com.example.oenskeskyen;

import com.example.oenskeskyen.models.Wish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.oenskeskyen.repositories.WishRepository;
import com.example.oenskeskyen.service.WishService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class WishServiceTest {

    private WishRepository wishRepository;
    private WishService wishService;

    @BeforeEach
    public void setup() {
        // Mock repositoryen
        wishRepository = mock(WishRepository.class);

        // Inject mocken ind i service
        wishService = new WishService(wishRepository);
    }

    @Test
    public void testFindWishByIdExists() {
        // Simuler et ønske
        Wish wish = new Wish(1, "Bog", 200.0, "En spændende bog", 1, "Bog.dk");

        // Fortæl mocken, hvad den skal returnere
        when(wishRepository.findWishById(1)).thenReturn(wish);

        // Kald metoden vi tester
        Wish result = wishService.findWishById(1);

        // Tjek resultaterne
        assertNotNull(result);                  // Ønsket skal ikke være null
        assertEquals("Bog", result.getTitle()); // Titlen skal være "Bog"

        // Tjek at repository-metoden blev kaldt præcis én gang
        verify(wishRepository, times(1)).findWishById(1);
    }

    @Test
    public void testDeleteWishSuccess() {
        // Simuler succesfuld sletning
        when(wishRepository.deleteWishById(1)).thenReturn(true);

        boolean deleted = wishService.deleteWish(1);

        assertTrue(deleted); // Skal returnere true
        verify(wishRepository, times(1)).deleteWishById(1);
    }
    @Test
    void testEditWish_Success() {
        // Arrange - lav et dummy wish-objekt
        Wish wish = new Wish(1, "Ny titel", 250.0, "Opdateret beskrivelse", 1, "Bog.dk");

        // Mock repository-responsen
        when(wishRepository.editWish(wish)).thenReturn(true);

        // Act - kald metoden i service
        boolean result = wishService.editWish(wish);

        // Assert - tjek at den returnerer true og kaldte repository'et
        assertTrue(result);
        verify(wishRepository, times(1)).editWish(wish);
    }

    @Test
    void addWish_ShouldCallRepository_WhenWishIsValid() {
        Wish wish = new Wish();
        wish.setTitle("Playstation");
        wish.setDescription("Spillekonsol");
        wish.setPrice(300.0);
        wish.setWishId(1);
        wish.setWishListId(3);

        wishService.addWishToWishlist(wish, wish.getWishListId());

        verify(wishRepository, times(1)).saveWish(wish);
    }

}


