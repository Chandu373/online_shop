package com.chandu.order_service.service;

import com.chandu.order_service.dto.InventoryResponse;
import com.chandu.order_service.dto.OrderLineItemsDTO;
import com.chandu.order_service.dto.OrderRequest;
import com.chandu.order_service.dto.OrderResponse;
import com.chandu.order_service.events.OrderEvent;
import com.chandu.order_service.model.Order;
import com.chandu.order_service.model.OrderLineItems;
import com.chandu.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
//    @Autowired
//    private KafkaOrderProducer kafkaOrderProducer;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static String INVENTORY_SERVICE_URL = "http://inventory-service/api/inventory";


    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItems(orderLineItemsList);

        List<String> skuCodesList = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        // call inventory service check is stock is available or not using webClient

        InventoryResponse[] result = webClientBuilder.build().get()
                .uri(INVENTORY_SERVICE_URL, uriBuilder -> uriBuilder.queryParam("skuCode", skuCodesList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean isStockAvailable = Arrays.stream(result).allMatch(inventoryResponse -> inventoryResponse.isInStock());
        if (!isStockAvailable) {
            throw new RuntimeException("Stock not available for selected products");
        }
        orderRepository.save(order);
        OrderEvent orderEvent = this.prepareOrderEvent(order,"Initiated");
//        kafkaOrderProducer.sendOrderEvent(orderEvent);
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

    public OrderEvent prepareOrderEvent(Order order,String status){
        return OrderEvent.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItems(order.getOrderLineItems())
                .status(status)
                .build();
    }

}
