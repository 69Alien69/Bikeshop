package ru.hackass122.bikeshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hackass122.bikeshop.dao.CartItemDAO;
import ru.hackass122.bikeshop.model.CartItem;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {
    private final CartItemDAO cartItemDAO;

    public CartItemServiceImpl(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    @Override
    public void delete(CartItem cartItem) {
        cartItemDAO.delete(cartItem);
    }

    @Override
    public List<CartItem> deleteAll(List<CartItem> cartItems) {
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            delete(iterator.next());
            iterator.remove();
        }
        return cartItems;
    }

    @Override
    public void deleteById(long id) {
        delete(cartItemDAO.findById(id).get());
    }
}
