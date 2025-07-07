package com.electronic.store.services;

import com.electronic.store.dtos.AddItemToCartRequest;
import com.electronic.store.dtos.CartDto;

public interface CartService {

    CartDto addItemToCart(String userid, AddItemToCartRequest addItemToCartRequest);

    void removeItemFromCart(String userId, long cartItem);

    void clearCart(String userid);

    CartDto getCartByUser(String userId);
}
