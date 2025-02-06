package ru.hackass122.bikeshop.dao;

import ru.hackass122.bikeshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {


}
