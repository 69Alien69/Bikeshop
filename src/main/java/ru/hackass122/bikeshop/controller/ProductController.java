package ru.hackass122.bikeshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hackass122.bikeshop.model.CartItem;
import ru.hackass122.bikeshop.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {

        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping("/product")
    public String getProduct(Model model, @RequestParam long productId) {
        model.addAttribute("product", productService.getProduct(productId).get());
        model.addAttribute("cartItem", new CartItem());
        return "product";
    }
}
