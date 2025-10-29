package controller;

import models.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.WishService;

@Controller
@RequestMapping
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
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
