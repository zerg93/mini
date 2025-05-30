package org.study.mini.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.mini.dto.TopicDto;
import org.study.mini.mapper.TopicMapper;
import org.study.mini.repository.TopicRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1. ClassName     : TopicService
 * 2. FileName      : TopicService.java
 * 3. Package       : org.study.mini.service
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 3:47
 * 6. Comment       :
 */
@Service
@RequiredArgsConstructor
public class TopicService {
    
    // TopicService에서 사용할 TopicRepository를 주입받아야 합니다.
    private final TopicRepository topicRepository;
    private static final AtomicReference<List<TopicDto>> CACHE = new AtomicReference<>(Collections.emptyList());
    
    @PostConstruct
    public void init() {
        reload();
    }
    
    public void reload() {
        // TopicRepository를 사용하여 모든 토픽을 조회하고, TopicMapper를 통해 TopicDto로 변환합니다.
        List<TopicDto> newList = topicRepository.findAllByUseYn("Y").stream()
            .map(TopicMapper::toDto)
            .toList();
        // 불변 리스트로 대입하여 CACHE를 갱신합니다.
        CACHE.set(newList);
    }
    
    // 예시로 TopicRepository를 사용하여 모든 토픽을 조회하는 메서드를 추가할 수 있습니다.
    public List<TopicDto> findAll() {
        return CACHE.get();
    }
}
