package org.study.mini.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.study.mini.dto.ApiDto;
import org.study.mini.mapper.ApiMapper;
import org.study.mini.repository.ApiRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1. ClassName     : ApiService
 * 2. FileName      : ApiService.java
 * 3. Package       : org.study.mini.service
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:32
 * 6. Comment       :
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ApiService {
    // ApiService에서 사용할 apiRepository 주입받아야 합니다.
    private final ApiRepository apiRepository;
    // ✅ 완전 thread-safe: 내부적으로 volatile + CAS를 활용
    private static final AtomicReference<List<ApiDto>> CACHE = new AtomicReference<>(Collections.emptyList());
    
    @PostConstruct
    public void init() {
        reload();
    }
    
    public void reload() {
        List<ApiDto> newList = apiRepository.findAllByUseYn("Y").stream()
            .map(ApiMapper::toDto)
            .toList();
        
        CACHE.set(newList);  // ✅ 불변 리스트로 대입
    }
    
    // 예시로 ApiRepository를 사용하여 모든 서비스를 조회하는 메서드를 추가할 수 있습니다.
    public List<ApiDto> findAll() {
        return CACHE.get();
    }
}
