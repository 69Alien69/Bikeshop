package ru.hackass122.bikeshop.service;

import ru.hackass122.bikeshop.model.CartItem;
import ru.hackass122.bikeshop.model.Order;
import ru.hackass122.bikeshop.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> convertCartItemsToOrderItems(List<CartItem> cartItems, Order order);
}
