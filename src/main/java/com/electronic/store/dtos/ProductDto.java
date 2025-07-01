package com.electronic.store.dtos;

import com.electronic.store.entities.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    @NotNull
    private String productId;

    @NotNull(message = "title is required")
    @Size(min = 3, max = 250, message = "Length of title should be greater than 3 and less than 250")
    private String title;

    @Size(max = 1000, message = "description length must be less then 1000 char")
    private String description;

    @Positive
    private int price;

    @Positive
    private int discountedPrice;

    @Positive
    private int quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;

    private CategoryDto category;
}