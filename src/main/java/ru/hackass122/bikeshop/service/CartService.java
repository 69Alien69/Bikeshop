package ru.hackass122.bikeshop.service;

import ru.hackass122.bikeshop.model.Cart;
import ru.hackass122.bikeshop.model.Product;

import java.math.BigDecimal;

public interface CartService {
    void addProductToCart(Product product, long userId, int quantity);
    Cart getCartByUserId(long userId);
    Cart createCart(long userId);
    void saveCart(Cart cart);
    BigDecimal calculateCost(Cart cart);
    Cart getCart(long id);
    void clear(Cart cart);
}
