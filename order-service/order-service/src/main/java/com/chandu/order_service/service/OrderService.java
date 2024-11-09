package com.chandu.order_service.service;

import com.chandu.order_service.dto.OrderLineItemsDTO;
import com.chandu.order_service.dto.OrderRequest;
import com.chandu.order_service.dto.OrderResponse;
import com.chandu.order_service.model.Order;
import com.chandu.order_service.model.OrderLineItems;
import com.chandu.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItems(orderLineItemsList);
        orderRepository.save(order);
        return orderToOrderResponse(order);
    }

    public OrderResponse orderToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItems(order.getOrderLineItems())
                .build();
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(this::orderToOrderResponse).toList();

    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        return orderLineItems;
    }

}
