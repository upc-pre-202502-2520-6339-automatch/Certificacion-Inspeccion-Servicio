package com.automatch.inspections.interfaces.web;

import com.automatch.inspections.infrastructure.messaging.producer.ProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestKafkaController {

    private final ProducerService producerService;

    public TestKafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/kafka/enviar/{msg}")
    public String testKafka(@PathVariable String msg) {
        producerService.sendMessage(msg);
        return "OK enviado -> " + msg;
    }
}