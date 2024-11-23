package com.chandu.notification_service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent implements Serializable {

    private Long orderId;
    private String orderNumber;
//    private List<OrderLineItems> orderLineItems;
    private String status;

}
