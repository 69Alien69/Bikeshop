package ru.hackass122.bikeshop.dao;

import ru.hackass122.bikeshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Long> {

}
