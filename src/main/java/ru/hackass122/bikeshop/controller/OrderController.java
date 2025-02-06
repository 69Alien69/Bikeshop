package ru.hackass122.bikeshop.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hackass122.bikeshop.model.Cart;
import ru.hackass122.bikeshop.model.Order;
import ru.hackass122.bikeshop.model.User;
import ru.hackass122.bikeshop.service.CartService;
import ru.hackass122.bikeshop.service.OrderService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;

    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostMapping
    public String placeOrder(@RequestParam long cartId) {
        Cart cart = cartService.getCart(cartId);
        orderService.createOrder(cart);
        return "redirect:orders";
    }

    @GetMapping
    public String allOrders(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("orders", orderService.getOrders(user));
        return "/orders";
    }

    @GetMapping("/order")
    public String showOrder(Model model, @RequestParam long orderId) {
        model.addAttribute("order", orderService.getOrder(orderId));
        return "/order";
    }
}
