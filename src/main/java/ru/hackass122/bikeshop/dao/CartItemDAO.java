package ru.hackass122.bikeshop.dao;

import ru.hackass122.bikeshop.model.Cart;
import ru.hackass122.bikeshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hackass122.bikeshop.model.Product;

import java.util.Optional;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findCartItemByCartAndProduct(Cart cart, Product product);
}
