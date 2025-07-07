package com.electronic.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private long cartItemId;
    private int quantity;
    private float totalPrice;
    private ProductDto product;
}
