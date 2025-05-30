package org.study.mini.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.mini.dto.InstDto;
import org.study.mini.mapper.InstMapper;
import org.study.mini.repository.InstRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1. ClassName     : InstService
 * 2. FileName      : InstService.java
 * 3. Package       : org.study.mini.service
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 25. 오후 9:34
 * 6. Comment       :
 */
@Service
@RequiredArgsConstructor
public class InstService {
    
    private final InstRepository instRepository;
    private static final AtomicReference<List<InstDto>> CACHE = new AtomicReference<>(Collections.emptyList());
    
    @PostConstruct
    public void init(){
        reload();
    }
    
    public void reload() {
        // InstRepository를 사용하여 모든 토픽을 조회하고, InstMapper를 통해 InstDto로 변환합니다.
        List<InstDto> newList = instRepository.findAllByUseYn("Y").stream()
            .map(InstMapper::toDto)
            .toList();
        // 불변 리스트로 대입하여 CACHE를 갱신합니다.
        CACHE.set(newList);
    }
    
    public List<InstDto> findAll() {
        return CACHE.get();
    }
}
