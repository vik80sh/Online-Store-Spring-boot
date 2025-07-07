package com.electronic.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    private String cartId;

    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items= new ArrayList<>();
}
