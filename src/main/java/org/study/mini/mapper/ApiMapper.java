package org.study.mini.mapper;

import org.study.mini.dto.ApiDto;
import org.study.mini.entity.Api;

/**
 * 1. ClassName     : ApiMapper
 * 2. FileName      : ApiMapper.java
 * 3. Package       : org.study.mini.mapper
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:25
 * 6. Comment       : Api 엔티티와 ApiDto 간의 매핑을 담당하는 Mapper 인터페이스
 */
public class ApiMapper {
    // Api 엔티티와 ApiDto 간의 매핑을 담당하는 Mapper 인터페이스
    // Api 엔티티를 ApiDto로 변환하는 메서드
    public static ApiDto toDto(Api api) {
        if (api == null) {
            return null;
        }
        return ApiDto.builder()
            .apiId(api.getApi_id())
            .serviceCode(api.getServiceCode())
            .apiEngName(api.getApiEngName())
            .apiKorName(api.getApiKorName())
            .requestUrl(api.getRequestUrl())
            .responseUrl(api.getResponseUrl())
            .reverseYn(api.getReverseYn())
            .useYn(api.getUseYn())
            .build();
    }
    
    // ApiDto를 Api 엔티티로 변환하는 메서드
    public static Api toEntity(ApiDto apiDto) {
        if (apiDto == null) {
            return null;
        }
        return Api.builder()
            .api_id(apiDto.getApiId())
            .serviceCode(apiDto.getServiceCode())
            .apiEngName(apiDto.getApiEngName())
            .apiKorName(apiDto.getApiKorName())
            .requestUrl(apiDto.getRequestUrl())
            .responseUrl(apiDto.getResponseUrl())
            .reverseYn(apiDto.getReverseYn())
            .useYn(apiDto.getUseYn())
            .build();
    }
}
