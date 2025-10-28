package controller;

import models.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.WishService;

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
            return "FindByWishId"; // viser fejlbesked i samme skabelon
        }

        model.addAttribute("wish", wish);
        return "FindByWishId"; // viser HTML-siden med ønskeinfo
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

        return "delete-wish"; // vis samme side igen med besked
    }

}
