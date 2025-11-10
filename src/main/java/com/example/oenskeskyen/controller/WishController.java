package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.models.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.oenskeskyen.service.WishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/wishes")
    public String getAllWishes (Model model) {
        List<Wish> wishes = wishService.getAllWishes();
        return "getAllWishes";
    }

    // GET /wishes/{id} - Hent et specifikt ønske
    @GetMapping("/wishes/{id}")
    public String showWish(@PathVariable int id, Model model) {
        Wish wish = wishService.findWishById(id);

        if (wish == null) {
            model.addAttribute("errorMessage", "Ønsket med ID " + id + " findes ikke.");
            return "findWishById"; // viser fejlbesked i samme skabelon
        }

        model.addAttribute("wish", wish);
        return "findWishById"; // viser HTML-siden med ønskeinfo
    }

    // DELETE /wishes/delete/{id}
    @PostMapping("/wishes/delete/{id}")
    public String deleteWish(@PathVariable int id, @RequestParam int wishlistId) {
        wishService.deleteWish(id);
        return "redirect:/wishlists/" + wishlistId;
    }



    // GET /wishes/edit/{id} - vis form med eksisterende data
    @GetMapping("/wishes/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Wish wish = wishService.findWishById(id);
        if (wish == null) {
            model.addAttribute("errorMessage", "Ønsket med ID " + id + " findes ikke.");
            return "editWish"; // viser HTML med fejl
        }
        model.addAttribute("wish", wish);
        return "editWish";
    }

    // POST /wishes/edit - gem opdateringer
    @PostMapping("/wishes/edit")
    public String editWish(@ModelAttribute Wish wish, Model model) {
        boolean updated = wishService.editWish(wish);
        if (updated) {
            model.addAttribute("message", "Ønsket med ID " + wish.getWishId() + " blev opdateret!");
        } else {
            model.addAttribute("message", "Ingen ønske fundet med ID " + wish.getWishId() + ".");
        }

        int wishlistId = wishService.getWishlistIdByWishId(wish.getWishId());
        return "redirect:/wishlists/" + wishlistId; // viser samme HTML igen med besked
    }

    @GetMapping("/wishes/add/{wishlistId}")
    public String addWish(@PathVariable int wishlistId, Model model) {
        System.out.println("Viser addWish side med wishlistId = " + wishlistId);
        model.addAttribute("wish", new Wish());
        model.addAttribute("wishlistId", wishlistId);
        return "addWish"; // henviser til templates/addWish.html
    }

    @PostMapping("/wishes/save/{wishlistId}")
    public String saveWish(@ModelAttribute Wish wish, @PathVariable int wishlistId) {
        System.out.println("modtaget wishlistId: " + wishlistId);
        System.out.println("Wish info: " + wish.getTitle() + ", " + wish.getDescription() + ", " + wish.getPrice() + ", " + wish.getUrl());
        wishService.addWishToWishlist(wish, wishlistId);
        return "redirect:/wishlists/" + wishlistId;
    }


}




