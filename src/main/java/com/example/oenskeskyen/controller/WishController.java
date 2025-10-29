package com.example.oenskeskyen.controller;

import com.example.oenskeskyen.models.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.oenskeskyen.service.WishService;

@Controller
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
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
    @PostMapping("/wishes/delete")
    public String deleteWishById(@RequestParam("wishId") int wishId, Model model) {
        boolean deleted = wishService.deleteWish(wishId);

        if (deleted) {
            model.addAttribute("message", "Ønsket med ID " + wishId + " blev slettet!");
        } else {
            model.addAttribute("message", "Ingen ønske fundet med ID " + wishId + ".");
        }

        return "deleteWish"; // vis samme side igen med besked
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
        return "editWish"; // viser samme HTML igen med besked
    }

    @GetMapping("/add/{wishlistId}")
    public String addWish(@PathVariable int wishlistId, Model model) {
        model.addAttribute("wish", new Wish());
        model.addAttribute("wishlistId", wishlistId);
        return "addWish"; // henviser til templates/addWish.html
    }

    @PostMapping("/save/{wishlistId}")
    public String saveWish(@ModelAttribute Wish wish, @PathVariable int wishlistId) {
        wishService.addWishToWishlist(wish, wishlistId);
        return "redirect:/wishes/success";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success"; // henviser til templates/success.html
    }
}




