package com.example.oenskeskyen;

import com.example.oenskeskyen.models.Wish;
import com.example.oenskeskyen.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.oenskeskyen.repositories.WishRepository;
import com.example.oenskeskyen.service.WishService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class WishServiceTest {

    private WishRepository wishRepository;
    private WishService wishService;
    private WishlistService wishlistService;

    @BeforeEach
    public void setup() {
        // Mock repositoryen
        wishRepository = mock(WishRepository.class);

        // Inject mocken ind i service
        wishService = new WishService(wishRepository, wishlistService, new JdbcTemplate());
    }

    @Test
    public void testFindWishByIdExists() {
        // Simuler et ønske
        Wish wish = new Wish(1, "AirPods Pro", 1999.00, "Trådløse høretelefoner fra Apple", 1, "apple.com");


        // Fortæl mocken, hvad den skal returnere
        when(wishRepository.findWishById(1)).thenReturn(wish);

        // Kald metoden vi tester
        Wish result = wishService.findWishById(1);

        // Tjek resultaterne
        assertNotNull(result);                  // Ønsket skal ikke være null
        assertEquals("AirPods Pro", result.getTitle()); // Titlen skal være "Bog"

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
        Wish wish = new Wish(1, "AirPods Pro", 1999.00, "Trådløse høretelefoner fra Apple", 1, "apple.com");



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
        // Mock JdbcTemplate
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class)).thenReturn(1);

        // Mock repository
        WishRepository wishRepository = mock(WishRepository.class);

        // Service med mocks
        WishService wishService = new WishService(wishRepository, jdbcTemplate);

        // Arrange
        Wish wish = new Wish();
        wish.setTitle("Playstation");
        wish.setDescription("Spillekonsol");
        wish.setPrice(300.0);
        wish.setWishId(1);
        wish.setWishListId(3);

        // Act
        wishService.addWishToWishlist(wish, wish.getWishListId());

        // Assert
        verify(wishRepository, times(1)).saveWish(wish);
        verify(wishRepository, times(1)).linkWishToWishlist(1, 3); // tjekker linkWishToWishlist også

    }
}


