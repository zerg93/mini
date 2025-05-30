package org.study.mini.mapper;

import org.study.mini.dto.TopicDto;
import org.study.mini.entity.Topic;

/**
 * 1. ClassName     : TopicMapper
 * 2. FileName      : TopicMapper.java
 * 3. Package       : org.study.mini.mapper
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 3:46
 * 6. Comment       : Topic 엔티티와 TopicDto 간의 매핑을 담당하는 Mapper 인터페이스
 */
public class TopicMapper {
    // Topic 엔티티를 TopicDto로 변환하는 메서드
    public static TopicDto toDto(Topic topic) {
        if (topic == null) {
            return null;
        }
        return TopicDto.builder()
            .topicId(topic.getTopicId())
            .topicCode(topic.getTopicCode())
            .useYn(topic.getUseYn())
            .build();
    }
    
    // TopicDto를 Topic 엔티티로 변환하는 메서드
    public static Topic toEntity(TopicDto topicDto) {
        if (topicDto == null) {
            return null;
        }
        return Topic.builder()
            .topicId(topicDto.getTopicId())
            .topicCode(topicDto.getTopicCode())
            .useYn(topicDto.getUseYn())
            .build();
    }
}
