package com.chandu.order_service.controller;


import com.chandu.order_service.dto.OrderRequest;
import com.chandu.order_service.dto.OrderResponse;
import com.chandu.order_service.model.Order;
import com.chandu.order_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        log.info("placeOrder order placed successfully " + orderResponse.getOrderNumber());
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(){
        List<OrderResponse> orderResponseList = orderService.getAllOrders();
        return ResponseEntity.ok(orderResponseList);
    }
}
