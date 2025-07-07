package com.electronic.store.dtos;

import com.electronic.store.entities.Order;
import com.electronic.store.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {
    private int quantity;

    private float totalPrice;

    private ProductDto product;

}
