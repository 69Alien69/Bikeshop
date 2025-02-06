package ru.hackass122.bikeshop.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hackass122.bikeshop.model.Cart;
import ru.hackass122.bikeshop.model.Product;
import ru.hackass122.bikeshop.model.User;
import ru.hackass122.bikeshop.service.CartItemService;
import ru.hackass122.bikeshop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam Product product, @AuthenticationPrincipal User user, @RequestParam int quantity) {
        cartService.addProductToCart(product, user.getId(), quantity);
        return "redirect:/products";
    }

    @GetMapping
    public String getCart(@AuthenticationPrincipal User user, Model model) {
        Cart cart = cartService.getCartByUserId(user.getId());
        model.addAttribute("cart", cart);
        model.addAttribute("cost", cartService.calculateCost(cart));
        return "/cart";
    }

    @PostMapping
    public String deleteCartItem(@RequestParam long cartItemId) {
        cartItemService.deleteById(cartItemId);
        return "redirect:cart";
    }
}
