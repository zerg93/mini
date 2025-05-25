package org.study.mini.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.study.mini.dto.InstDto;
import org.study.mini.service.InstService;

import java.util.List;

/**
 * 1. ClassName     : ValidProcessor
 * 2. FileName      : ValidProcessor.java
 * 3. Package       : org.study.mini.processor
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 25. 오후 9:33
 * 6. Comment       :
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ValidProcessor implements Processor {
    
    private final InstService instService;
    
    @Override
    public void process(Exchange exchange) throws Exception {
        List<InstDto> instDtoList = instService.findAll();
        log.info("instDtoList: {}", instDtoList);
    }
}
