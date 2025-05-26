package org.study.mini.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 1. ClassName     : RequestReplyProcessor
 * 2. FileName      : RequestReplyProcessor.java
 * 3. Package       : org.study.mini.kafka
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 26. 오후 9:20
 * 6. Comment       :
 */
@Component
@Slf4j
public class RequestReplyProcessor implements Processor {
    
    
    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("RequestReplyProcessor: {}", exchange.getIn().getBody());
        
        
    }
}
