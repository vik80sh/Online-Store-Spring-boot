package com.electronic.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="orders")
public class Order {

    @Id
    private String orderId;

    private String orderStatus; // Pending | Dispatched | Delivered

    private String paymentStatus;  // Paid | NotPaid

    private float orderAmount;

    @Column(length = 1000)
    private String billingAddress;

    private String billingName;

    private String billingPhoneNumber;

    private Date orderDate;

    private Date deliverDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems= new ArrayList<>();

}
