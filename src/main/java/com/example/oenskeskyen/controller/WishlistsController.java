package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.models.WishList;
import com.example.oenskeskyen.repositories.WishlistRowMapper;
import com.example.oenskeskyen.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishlistsController {

   private final WishlistService wishlistService;

   public WishlistsController(WishlistService wishlistService) {
       this.wishlistService = wishlistService;
   }



   //findWishlistById
   @GetMapping("/wishlists/{id}")
   public String showWishlist(@PathVariable int id, Model model) {
       WishList wishlist = wishlistService.findWishlistById(id);

       if (wishlist == null) {
           model.addAttribute("errorMessage", "Ønskelisten med ID " + id + " findes ikke.");
           return "findWishlistById"; // viser fejl
       }

       model.addAttribute("wishlist", wishlist);
       return "findWishlistById"; // viser HTML-side med ønskelisten
   }


    // GET: viser siden, så brugeren kan bekræfte sletning
    @GetMapping("/wishlists/delete/{id}")
    public String showDeletePage(@PathVariable int id, Model model) {
        model.addAttribute("wishlistId", id);
        return "deleteWishlist";
    }

    // POST: udfører selve sletningen
    @PostMapping("/wishlists/delete/{id}")
    public String deleteWishlist(@PathVariable int id, Model model) {
        boolean deleted = wishlistService.deleteWishlist(id);

        if (deleted) {
            model.addAttribute("message", "Ønskelisten med ID " + id + " blev slettet!");
        } else {
            model.addAttribute("message", "Ingen ønskeliste fundet med ID " + id + ".");
        }

        return "deleteWishlist";
    }

    // REDIGER ØNSKELIST
    @GetMapping("/wishlists/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        WishList wishlist = wishlistService.findWishlistById(id);
        if (wishlist == null) {
            model.addAttribute("errorMessage", "Ønskelisten med ID " + id + " findes ikke.");
            return "editWishlist";
        }
        model.addAttribute("wishlist", wishlist); // <-- vigtigt: navnet "wishlist" matcher th:object="${wishlist}"
        return "editWishlist";
    }
    @GetMapping("/wishlist/add/{ownerId}")
    public String addWishlist(@PathVariable int ownerId, Model model) {
        model.addAttribute("wishlist", new WishList());
        model.addAttribute("ownerId", ownerId);
        return "addWishlist"; // refererer til templates/addWishlist.html
    }

    // Gemmer ønskelisten i databasen
    @PostMapping("/wishlist/save/{ownerId}")
    public String saveWishlist(@ModelAttribute WishList wishlist, @PathVariable int ownerId) {
        wishlist.setUserId(ownerId);
        wishlistService.createWishlist(wishlist);
        return "redirect:/wishlist/add/{ownerId}";
    }
}


