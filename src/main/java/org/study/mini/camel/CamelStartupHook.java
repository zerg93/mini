package org.study.mini.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelStartupHook {

    @Bean
    public CamelContextConfiguration preRouteHook() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                System.out.println("✨ Camel 라우트 실행 전에 필요한 작업 수행!");
                // 여기에 라우트 빌드나 초기화 작업을 추가할 수 있습니다.
            }

            @Override
            public void afterApplicationStart(CamelContext context) {
                System.out.println("✅ Camel 라우트 실행 완료 후");
            }
        };
    }
}
