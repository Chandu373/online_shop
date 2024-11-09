package com.chandu.order_service.dto;

import com.chandu.order_service.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {

    private long id;
    private String orderNumber;
    private List<OrderLineItems> orderLineItems;
}
