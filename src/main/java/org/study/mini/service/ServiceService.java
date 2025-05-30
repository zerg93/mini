package org.study.mini.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.mini.dto.ServiceDto;
import org.study.mini.mapper.ServiceMapper;
import org.study.mini.repository.ServiceRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1. ClassName     : ServiceService
 * 2. FileName      : ServiceService.java
 * 3. Package       : org.study.mini.service
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 4:13
 * 6. Comment       :
 */
@Service
@RequiredArgsConstructor
public class ServiceService {
    // ServiceService에서 사용할 ServiceRepository를 주입받아야 합니다.
    private final ServiceRepository serviceRepository;
    // ✅ 완전 thread-safe: 내부적으로 volatile + CAS를 활용
    private static final AtomicReference<List<ServiceDto>> CACHE = new AtomicReference<>(Collections.emptyList());
    
    // ServiceService에서 사용할 ServiceRepository를 주입받아야 합니다.
    // 예시로 ServiceRepository를 사용하여 모든 서비스를 조회하는 메서드를 추가할 수 있습니다.
    @PostConstruct
    public void init() {
        reload();
    }
    
    public void reload() {
        List<ServiceDto> newList = serviceRepository.findAllByUseYn("Y").stream()
            .map(ServiceMapper::toDto)
            .toList();
        
        CACHE.set(newList);  // ✅ 불변 리스트로 대입
    }
    
    // 예시로 ServiceRepository를 사용하여 모든 서비스를 조회하는 메서드를 추가할 수 있습니다.
    public List<ServiceDto> findAll() {
        return CACHE.get();
    }
}
