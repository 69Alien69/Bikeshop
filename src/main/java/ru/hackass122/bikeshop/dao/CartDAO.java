package ru.hackass122.bikeshop.dao;

import ru.hackass122.bikeshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
