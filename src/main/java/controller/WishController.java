package controller;

import models.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import service.WishService;

@Controller
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    // GET /wishes/{id} - Hent et specifikt ønske
    @GetMapping("/{id}")
    public Wish showWish(@PathVariable int id) {
        Wish wish = wishService.findWishById(id);
        if (wish == null) {
            throw new RuntimeException("Ønsket med id " + id + " findes ikke");
        }
        return wish;
    }

    // DELETE /wishes/{id}
    @DeleteMapping("/{id}")
    public String deleteWishById(@PathVariable int id) {
        boolean deleted = wishService.deleteWish(id);
        if (deleted) {
            return "Ønsket med ID " + id + " blev slettet.";
        } else {
            return "Ingen ønske fundet med ID " + id + ".";
        }
    }


}
