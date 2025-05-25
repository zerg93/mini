package org.study.mini.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.mini.dto.InstDto;
import org.study.mini.mapper.InstMapper;
import org.study.mini.repository.InstRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    
    public List<InstDto> findAll() {
        return instRepository.findAllByUseYn("Y").stream()
            .map(InstMapper::toDto)
            .collect(Collectors.toList());
    }
}
