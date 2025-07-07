package com.electronic.store.dtos;

import com.electronic.store.entities.CartItem;
import com.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartDto {

    private String cartId;
    private Date createdAt;
    private User user;
    private List<CartItemDto> items= new ArrayList<>();
}
