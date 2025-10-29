package com.example.oenskeskyen;

import com.example.oenskeskyen.repositories.WishlistRepository;
import com.example.oenskeskyen.service.WishlistService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class WishlistServiceTest {



    @Test
    void deleteWishlist_returnsTrue_whenRepositoryDeletesSuccessfully() {
        // Arrange
        WishlistRepository mockRepo = mock(WishlistRepository.class);
        WishlistService service = new WishlistService(mockRepo);

        when(mockRepo.deleteWishlistById(1)).thenReturn(true);

        // Act
        boolean result = service.deleteWishlist(1);

        // Assert
        assertTrue(result);
        verify(mockRepo, times(1)).deleteWishlistById(1);
    }
}
