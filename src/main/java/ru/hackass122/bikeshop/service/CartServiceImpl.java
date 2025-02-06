package ru.hackass122.bikeshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hackass122.bikeshop.dao.CartDAO;
import ru.hackass122.bikeshop.dao.CartItemDAO;
import ru.hackass122.bikeshop.model.Cart;
import ru.hackass122.bikeshop.model.CartItem;
import ru.hackass122.bikeshop.model.Product;
import ru.hackass122.bikeshop.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;
    private final CartItemDAO cartItemDAO;
    private final ProductService productService;
    private final UserService userService;
    private final CartItemService cartItemService;

    public CartServiceImpl(CartDAO cartDAO, CartItemDAO cartItemDAO, ProductService productService, UserService userService, CartItemService cartItemService) {
        this.cartDAO = cartDAO;
        this.cartItemDAO = cartItemDAO;
        this.productService = productService;
        this.userService = userService;
        this.cartItemService = cartItemService;
    }

    @Override
    @Transactional
    public void addProductToCart(Product product, long userID, int quantity) {
        Cart cart = getCartByUserId(userID);
        Optional<CartItem> cartItemOptional = cartItemDAO.findCartItemByCartAndProduct(cart, product);
        CartItem cartItem;

        if (cartItemOptional.isPresent()) {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(quantity);
        }
        cartItemDAO.save(cartItem);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Cart getCartByUserId(long userId) {
        return cartDAO.findByUserId(userId).orElseGet(() -> createCart(userId));
    }

    @Override
    public Cart createCart(long userId) {
        User user = userService.getUser(userId);
        Cart cart = new Cart();
        cart.setUser(user);
        saveCart(cart);
        return cart;
    }

    @Override
    @Transactional
    public void saveCart(Cart cart) {
        cartDAO.save(cart);
    }

    @Override
    public BigDecimal calculateCost(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        BigDecimal cost = new BigDecimal(0);
        for (CartItem cartItem : cartItems) {
            for (int i = 0; i < cartItem.getQuantity(); i++ ) {
                cost = cost.add(cartItem.getPrice());
            }
        }
        return cost;
    }

    @Override
    public Cart getCart(long id) {
        return cartDAO.findById(id).get();
    }

    @Override
    public void clear(Cart cart) {
        cart.setCartItems(cartItemService.deleteAll(cart.getCartItems()));
    }
}
