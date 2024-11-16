/*
package com.chandu.order_service.config;

import com.chandu.order_service.events.OrderEvent;
import com.chandu.order_service.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaOrderProducer.class);

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private String topic;

    public KafkaOrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(topic, orderEvent);
        log.info("Order event sent to kafka " + orderEvent);
    }
}
*/
