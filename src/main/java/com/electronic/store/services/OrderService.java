package com.electronic.store.services;

import com.electronic.store.dtos.OrderDto;
import com.electronic.store.dtos.PageableResponse;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto,String userId, String cartId);

    void removeOrder(String UserId);

    List<OrderDto> getOrderOfUser(String userid);

    PageableResponse<OrderDto> getOrder(int PageNumber, int pageSize, String sortBy, String OrderDir);

}
