package org.study.mini.mapper;

import org.study.mini.dto.ServiceDto;
import org.study.mini.entity.Service;

/**
 * 1. ClassName     : ServiceMapper
 * 2. FileName      : ServiceMapper.java
 * 3. Package       : org.study.mini.mapper
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 4:09
 * 6. Comment       : Service 엔티티와 ServiceDto 간의 매핑을 담당하는 Mapper 인터페이스
 */
public class ServiceMapper {
    // Service 엔티티를 ServiceDto로 변환하는 메서드
    public static ServiceDto toDto(Service service) {
        if (service == null) {
            return null;
        }
        return ServiceDto.builder()
            .serviceId(service.getServiceId())
            .serviceCode(service.getServiceCode())
            .serviceEngName(service.getServiceEngName())
            .serviceKorName(service.getServiceKorName())
            .instCode(service.getInstCode())
            .topicCode(service.getTopicCode())
            .useYn(service.getUseYn())
            .build();
    }
    
    // ServiceDto를 Service 엔티티로 변환하는 메서드
    public static Service toEntity(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        return Service.builder()
            .serviceId(serviceDto.getServiceId())
            .serviceCode(serviceDto.getServiceCode())
            .serviceEngName(serviceDto.getServiceEngName())
            .serviceKorName(serviceDto.getServiceKorName())
            .instCode(serviceDto.getInstCode())
            .topicCode(serviceDto.getTopicCode())
            .useYn(serviceDto.getUseYn())
            .build();
    }
}
