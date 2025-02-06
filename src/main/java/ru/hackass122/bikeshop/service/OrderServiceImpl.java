package ru.hackass122.bikeshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hackass122.bikeshop.dao.OrderDAO;
import ru.hackass122.bikeshop.model.Cart;
import ru.hackass122.bikeshop.model.Order;
import ru.hackass122.bikeshop.model.OrderItem;
import ru.hackass122.bikeshop.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final OrderItemService orderItemsService;
    private final CartService cartService;
    private final ProductService productService;

    public OrderServiceImpl(OrderDAO orderDAO, OrderItemService orderItemsService, CartService cartService, ProductService productService) {
        this.orderDAO = orderDAO;
        this.orderItemsService = orderItemsService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    @Transactional
    public void createOrder(Cart cart) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(cart.getUser());
        order.setTotalAmount(cartService.calculateCost(cart));
        orderDAO.save(order);
        order = orderDAO.findOrderByUserAndOrderDate(order.getUser(), order.getOrderDate()).get(0);
        order.setOrderItems(orderItemsService.convertCartItemsToOrderItems(cart.getCartItems(), order));
        orderDAO.save(order);
        cartService.clear(cart);
        productService.sellProducts(order.getOrderItems());
    }

    @Override
    public List<Order> getOrders(User user) {
        return orderDAO.findOrdersByUser(user);
    }

    @Override
    public Order getOrder(long orderId) {
        return orderDAO.findById(orderId).get();
    }
}
