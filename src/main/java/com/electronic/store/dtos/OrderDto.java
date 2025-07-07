package com.electronic.store.dtos;

import com.electronic.store.entities.OrderItem;
import com.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {

    @Id
    private String orderId;

    private String orderStatus = "PENDING"; // Pending | Dispatched | Delivered

    private String paymentStatus="NOTPAID";  // Paid | NotPaid

    private float orderAmount;

    private String billingAddress;

    private String billingName;

    private String billingPhoneNumber;

    private Date orderDate= new Date();

    private Date deliverDate;

    private UserDto user;

    private List<OrderItemDto> orderItems= new ArrayList<>();
}
