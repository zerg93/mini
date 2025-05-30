package org.study.mini.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Component;
import org.study.mini.dto.ServiceDto;
import org.study.mini.service.ServiceService;

import java.util.concurrent.TimeUnit;

/**
 * 1. ClassName     : RequestReplyProcessor
 * 2. FileName      : RequestReplyProcessor.java
 * 3. Package       : org.study.mini.kafka
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 26. 오후 9:20
 * 6. Comment       :
 */
@Component
@Slf4j
@RequiredArgsConstructor
@Profile("was")
public class RequestReplyProcessor implements Processor {
    
    private final ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;
    private final ServiceService serviceService;
    
    @Override
    public void process(Exchange exchange) throws Exception {
        
        String request = exchange.getMessage().getBody(String.class);
        
        ServiceDto service = serviceService.findAll().stream().filter(v -> v.getServiceEngName().equals(exchange.getMessage().getHeader("service"))).findFirst().orElse(null);
        if (service == null) {
            log.info("Service not found for header: {}", exchange.getMessage().getHeader("service"));
            throw new IllegalArgumentException("Service not found");
        }
        // ✅ REPLY_TOPIC, CORRELATION_ID 꼭 포함
        ProducerRecord<String, String> record = new ProducerRecord<>("request."+service.getTopicCode(), request);
        log.info("Sending request: {}", record);
        
        RequestReplyFuture<String, String, String> future = replyingKafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, String> response = future.get(5, TimeUnit.SECONDS);
        
        log.info("📥 Kafka 응답 수신: value={}", response.value());
        log.info("📥 Kafka 응답 수신: value={}", response.headers());
        
        exchange.getMessage().setBody(response.value());
    }
    
}
