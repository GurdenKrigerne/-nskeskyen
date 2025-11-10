package com.example.oenskeskyen;

import com.example.oenskeskyen.models.WishList;
import com.example.oenskeskyen.repositories.WishlistRepository;
import com.example.oenskeskyen.service.WishService;
import com.example.oenskeskyen.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WishlistServiceTest {



    @Test
    void deleteWishlist_returnsTrue_whenRepositoryDeletesSuccessfully() {
        // Arrange
        WishlistRepository mockRepo = mock(WishlistRepository.class);
        WishService mockService = mock(WishService.class);
        WishlistService service = new WishlistService(mockRepo, mockService);

        when(mockRepo.deleteWishlistById(1)).thenReturn(true);

        // Act
        boolean result = service.deleteWishlist(1);

        // Assert
        assertTrue(result);
        verify(mockRepo, times(1)).deleteWishlistById(1);
    }

    @Test
    void testFindWishlistById_returnsWishlist_whenFound() {

        // Mock test data
        WishlistRepository mockRepo = Mockito.mock(WishlistRepository.class);
        WishService mockService = mock(WishService.class);
        WishlistService service = new WishlistService(mockRepo, mockService);

        WishList fakeWishlist = new WishList(1, "Fødselsdag", "Ting jeg ønsker mig", 1);
        when(mockRepo.findWishlistById(1)).thenReturn(fakeWishlist);

        // Act (kald metoden vi tester)
        WishList result = service.findWishlistById(1);

        // Assert (tjek at resultatet er som forventet)
        assertNotNull(result);
        assertEquals("Fødselsdag", result.getTitle());
        verify(mockRepo, times(1)).findWishlistById(1);
    }

    @Test
    void testEditWishlist_returnsTrue_whenUpdateSuccessful() {
        // Arrange
        WishlistRepository mockRepo = mock(WishlistRepository.class);
        WishService mockService = mock(WishService.class);
        WishlistService service = new WishlistService(mockRepo, mockService);

        WishList wishlist = new WishList(1, "Jul", "Nye ønsker", 1);
        when(mockRepo.editWishlist(wishlist)).thenReturn(true);

        // Act
        boolean result = service.editWishlist(wishlist);

        // Assert
        assertTrue(result);
        verify(mockRepo).editWishlist(wishlist);
    }


}
