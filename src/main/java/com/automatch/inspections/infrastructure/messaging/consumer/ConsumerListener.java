package com.automatch.inspections.infrastructure.messaging.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    @KafkaListener(topics = "${automatch.topic.consumer}", groupId = "inspections-service-group")
    public void listen(String message) {
        System.out.println("EVENT RECEIVED FROM PAYMENT SERVICE: " + message);

        // *** aquí tú puedes luego llamar a tu lógica de dominio:
        // inspección automática, certificado, etc.
    }
}