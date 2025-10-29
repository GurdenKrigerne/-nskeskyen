package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishlistsController {

   private final WishlistService wishlistService;

   public WishlistsController(WishlistService wishlistService) {
       this.wishlistService = wishlistService;
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
}


