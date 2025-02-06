package ru.hackass122.bikeshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hackass122.bikeshop.model.CartItem;
import ru.hackass122.bikeshop.model.Order;
import ru.hackass122.bikeshop.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {
    @Override
    public List<OrderItem> convertCartItemsToOrderItems(List<CartItem> cartItems, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
