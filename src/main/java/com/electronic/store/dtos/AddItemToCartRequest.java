package com.electronic.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddItemToCartRequest {
    private String productId;

    private int quantity;
}
