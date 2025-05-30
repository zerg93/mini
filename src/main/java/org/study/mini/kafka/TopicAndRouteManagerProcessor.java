package org.study.mini.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("proxy")
public class TopicAndRouteManagerProcessor implements Processor {

    private final KafkaTopicCreator kafkaTopicCreator;
    private final DynamicRouteRegistrar dynamicRouteRegistrar;

    @Override
    public void process(Exchange exchange) throws Exception {
        String topic = exchange.getIn().getHeader("dynamicTopic", String.class);
        boolean created = kafkaTopicCreator.createTopicIfNotExists(topic, 3, (short) 3);
        dynamicRouteRegistrar.registerKafkaConsumerRoute(topic);
        exchange.getMessage().setBody("토픽: " + topic + ", 생성됨: " + created);
    }
}
