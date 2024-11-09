package com.chandu.order_service.service;

import com.chandu.order_service.dto.OrderRequest;
import com.chandu.order_service.dto.OrderResponse;
import com.chandu.order_service.model.Order;
import com.chandu.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public OrderResponse create(OrderRequest orderRequest) {
        Order order = orderRepository.save(orderRequestToOrder(orderRequest));
        return orderToOrderResponse(order);
    }


    public Order orderRequestToOrder(OrderRequest request) {
        return Order.builder()
                .id(request.getId())
                .orderNumber(request.getOrderNumber())
                .build();
    }

    public OrderResponse orderToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .build();

    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(this::orderToOrderResponse).toList();

    }
}
