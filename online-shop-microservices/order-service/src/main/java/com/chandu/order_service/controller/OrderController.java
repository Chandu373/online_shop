package com.chandu.order_service.controller;


import com.chandu.order_service.dto.OrderRequest;
import com.chandu.order_service.dto.OrderResponse;
import com.chandu.order_service.model.Order;
import com.chandu.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
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

    private static final String INVENTORY_SERVICE = "inventory";

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = INVENTORY_SERVICE, fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = INVENTORY_SERVICE)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        try{
            OrderResponse orderResponse = orderService.placeOrder(orderRequest);
            log.info("placeOrder order placed successfully " + orderResponse.getOrderNumber());
        }catch (Exception errMsg){
            log.info("placeOrder Exception occurred while placing order "+errMsg.getMessage());
        }
        return "order placed successfully";
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders() {
        List<OrderResponse> orderResponseList = orderService.getAllOrders();
        return ResponseEntity.ok(orderResponseList);
    }

    public String fallBackMethod(Exception e) {
        log.info("inventory service is not available");
        return "fallBackMethod inventory service is not available";
    }
}
