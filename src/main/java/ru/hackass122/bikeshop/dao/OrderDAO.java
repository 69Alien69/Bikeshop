package ru.hackass122.bikeshop.dao;

import ru.hackass122.bikeshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hackass122.bikeshop.model.User;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUser(User user);
    List<Order> findOrderByUserAndOrderDate(User user, LocalDateTime orderDate);
}
