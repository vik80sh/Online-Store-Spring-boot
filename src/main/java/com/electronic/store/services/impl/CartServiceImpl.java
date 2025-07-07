package com.electronic.store.services.impl;

import com.electronic.store.dtos.AddItemToCartRequest;
import com.electronic.store.dtos.CartDto;
import com.electronic.store.entities.Cart;
import com.electronic.store.entities.CartItem;
import com.electronic.store.entities.Product;
import com.electronic.store.entities.User;
import com.electronic.store.exceptions.BadApiRequestException;
import com.electronic.store.exceptions.ResourceNotFoundException;
import com.electronic.store.repositories.CartItemRepository;
import com.electronic.store.repositories.CartRepository;
import com.electronic.store.repositories.ProductRepository;
import com.electronic.store.repositories.UserRepository;
import com.electronic.store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CartDto addItemToCart(String userid, AddItemToCartRequest request) {

        int quantity= request.getQuantity();
        String productId= request.getProductId();

        if(quantity<=0){
            throw new BadApiRequestException("Requested quality is not valid");
        }

        Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(" Product Not found"));

        User user= userRepository.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCartId(UUID.randomUUID().toString());
            newCart.setCreatedAt(new Date());
            newCart.setItems(new ArrayList<>());
            newCart.setUser(user);
            return newCart;
        });
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        List<CartItem> items = cart.getItems();

        boolean updated = false;
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getPrice());
                updated = true;
                break;
            }
        }
        if(!updated) {
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getPrice())
                    .cart(cart)
                    .product(product)
                    .build();

            cart.getItems().add(cartItem);
        }
        cart.setUser(user);

        Cart updatedCart= cartRepository.save(cart);

        return modelMapper.map(updatedCart, CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, long cartItemId) {
        CartItem cartItem=cartItemRepository.findById(cartItemId).orElseThrow(()->new ResourceNotFoundException("Cart Item not found"));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void clearCart(String userid) {
        User user= userRepository.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User is not valid"));
        Cart cart= cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("No cart found for this user"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId){
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Cart is not available"));

        return modelMapper.map(cart, CartDto.class);
    }
}
