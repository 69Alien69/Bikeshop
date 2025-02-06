package ru.hackass122.bikeshop.service;

import ru.hackass122.bikeshop.model.OrderItem;
import ru.hackass122.bikeshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> getProduct(long id);
    void saveProduct(Product product);
    void sellProducts(List<OrderItem> orderItems);
}
