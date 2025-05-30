package org.study.mini.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
@Profile("proxy")
public class KafkaTopicCreator {

    private final AdminClient adminClient;

    public KafkaTopicCreator() {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        this.adminClient = AdminClient.create(config);
    }

    public boolean createTopicIfNotExists(String topicName, int partitions, short replicationFactor) throws Exception {
        if (topicExists(topicName)) {
            log.info("✔ 이미 존재하는 토픽: {}", topicName);
            return false;
        }

        NewTopic requestTopic = new NewTopic("request."+topicName, partitions, replicationFactor);
        NewTopic responseTopic = new NewTopic("response."+topicName, partitions, replicationFactor);
        adminClient.createTopics(Collections.singletonList(requestTopic)).all().get();
        adminClient.createTopics(Collections.singletonList(responseTopic)).all().get();
        log.info("✅ 토픽 생성 완료: {}", topicName);
        return true;
    }

    public boolean topicExists(String topicName) throws ExecutionException, InterruptedException {
        ListTopicsResult topics = adminClient.listTopics();
        Set<String> names = topics.names().get();
        return names.contains(topicName);
    }

    public void close() {
        adminClient.close();
    }
}
