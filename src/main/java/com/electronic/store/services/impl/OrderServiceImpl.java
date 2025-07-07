package com.electronic.store.services.impl;

import com.electronic.store.dtos.OrderDto;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.entities.*;
import com.electronic.store.exceptions.BadApiRequestException;
import com.electronic.store.exceptions.ResourceNotFoundException;
import com.electronic.store.repositories.CartRepository;
import com.electronic.store.repositories.OrderRepository;
import com.electronic.store.repositories.UserRepository;
import com.electronic.store.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto, String userId, String cartId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not find"));

        Cart cart= cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart not found"));

        List<CartItem> items= cart.getItems();

        if(items.size()<=0){
            throw new BadApiRequestException("Invalid number of cart");
        }

        Order order= Order.builder()
                .billingAddress(orderDto.getBillingAddress())
                .billingName(orderDto.getBillingName())
                .billingPhoneNumber(orderDto.getBillingPhoneNumber())
                .orderDate(new Date())
                .orderId(UUID.randomUUID().toString())
                .deliverDate(orderDto.getDeliverDate())
                .orderStatus(orderDto.getOrderStatus())
                .user(user)
                .build();


        List<OrderItem> orderItems= items.stream().map(item->{
            return OrderItem.builder()
                    .quantity(item.getQuantity())
                    .product(item.getProduct())
                    .totalPrice(item.getQuantity() * item.getProduct().getDiscountedPrice())
                    .order(order)
                    .build();
        }).collect(Collectors.toList());

        float orderAmount = (float) orderItems.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
        order.setOrderItems(orderItems);
        order.setOrderAmount(orderAmount);

        cart.getItems().clear();
        cartRepository.save(cart);

        Order saveOrder= orderRepository.save(order);

        return modelMapper.map(saveOrder, OrderDto.class);

    }

    @Override
    public void removeOrder(String UserId) {

    }

    @Override
    public List<OrderDto> getOrderOfUser(String userid) {
        return List.of();
    }

    @Override
    public PageableResponse<OrderDto> getOrder(int PageNumber, int pageSize, String sortBy, String OrderDir) {
        return null;
    }
}
