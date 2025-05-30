package org.study.mini.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("proxy")
public class DynamicRouteRegistrar {

    private final CamelContext camelContext;

    public void registerKafkaConsumerRoute(String topicName) throws Exception {
        String requestRouteId = "request." + topicName;
        String responseRouteId = "response." + topicName;

        if (camelContext.getRouteController().getRouteStatus(requestRouteId) != null) {
            log.info("✔ 이미 존재하는 라우트: {}", requestRouteId);
            return;
        }
        if (camelContext.getRouteController().getRouteStatus(responseRouteId) != null) {
            log.info("✔ 이미 존재하는 라우트: {}", responseRouteId);
            return;
        }

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("kafka:" + topicName + "?brokers={{spring.kafka.bootstrap-servers}}")
                    .routeId(requestRouteId)
                    .log("🔥 동적 Kafka 수신 - 토픽: " + topicName + ", 메시지: ${body}");
            }
        });
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("kafka:" + topicName + "?brokers={{spring.kafka.bootstrap-servers}}")
                    .routeId(responseRouteId)
                    .log("🔥 동적 Kafka 수신 - 토픽: " + topicName + ", 메시지: ${body}");
            }
        });

        log.info("✅ 라우트 생성 완료: {}", requestRouteId);
        log.info("✅ 라우트 생성 완료: {}", responseRouteId);
    }
}
