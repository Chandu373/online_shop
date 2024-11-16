package com.chandu.order_service.events;

import com.chandu.order_service.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent implements Serializable {

    private Long orderId;
    private String orderNumber;
    private List<OrderLineItems> orderLineItems;
    private String status;

}
