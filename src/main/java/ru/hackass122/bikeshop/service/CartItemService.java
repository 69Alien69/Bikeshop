package ru.hackass122.bikeshop.service;

import ru.hackass122.bikeshop.model.CartItem;

import java.util.List;

public interface CartItemService {
    void delete(CartItem cartItem);
    void deleteById(long id);
    List<CartItem> deleteAll(List<CartItem> cartItems);
}
