package org.study.mini.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.time.Duration;

@Configuration
@Profile({"was","proxy"})
public class KafkaRequestReplyConfig {
    
    private String replyTopic;
    
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> repliesContainer) {
        ReplyingKafkaTemplate<String, String, String> replyTemplate = new ReplyingKafkaTemplate<>(pf, repliesContainer);
        replyTemplate.setDefaultReplyTimeout(Duration.ofMillis(5000));
        replyTemplate.setSharedReplyTopic(true);
        return replyTemplate;
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> repliesContainer(
            ConsumerFactory<String, String> cf) {
        ContainerProperties containerProperties = new ContainerProperties("response.T00001");
        containerProperties.setGroupId(groupId);
        return new ConcurrentMessageListenerContainer<>(cf, containerProperties);
    }
}