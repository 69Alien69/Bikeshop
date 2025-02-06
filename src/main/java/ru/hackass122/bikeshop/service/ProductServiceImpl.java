package ru.hackass122.bikeshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hackass122.bikeshop.dao.ProductDAO;
import ru.hackass122.bikeshop.model.OrderItem;
import ru.hackass122.bikeshop.model.Product;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    @Override
    public Optional<Product> getProduct(long id) {
        return productDAO.findById(id);
    }

    @Override
    public void sellProducts(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            product.setStock(product.getStock() - orderItem.getQuantity());
            productDAO.save(product);
        }
    }
}
