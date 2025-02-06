package ru.hackass122.bikeshop.service;

import ru.hackass122.bikeshop.model.Cart;
import ru.hackass122.bikeshop.model.Order;
import ru.hackass122.bikeshop.model.User;

import java.util.List;

public interface OrderService {
    void createOrder(Cart cart);
    List<Order> getOrders(User user);
    Order getOrder(long orderId);
}
