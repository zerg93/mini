package org.study.mini.mapper;

import org.study.mini.dto.LinkSystemDto;
import org.study.mini.entity.LinkSystem;

/**
 * 1. ClassName     : LinkSystemMapper
 * 2. FileName      : LinkSystemMapper.java
 * 3. Package       : org.study.mini.mapper
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:21
 * 6. Comment       : LinkSystem 엔티티와 LinkSystemDto 간의 매핑을 담당하는 Mapper 인터페이스
 */
public class LinkSystemMapper {
    // LinkSystem 엔티티를 LinkSystemDto로 변환하는 메서드
    public static LinkSystemDto toDto(LinkSystem linkSystem) {
        if (linkSystem == null) {
            return null;
        }
        return LinkSystemDto.builder()
            .linkSystemId(linkSystem.getLinkSystemId())
            .instCode(linkSystem.getInstCode())
            .systemName(linkSystem.getSystemName())
            .ipAddress(linkSystem.getIpAddress())
            .useYn(linkSystem.getUseYn())
            .build();
    }
    
    // LinkSystemDto를 LinkSystem 엔티티로 변환하는 메서드
    public static LinkSystem toEntity(LinkSystemDto linkSystemDto) {
        if (linkSystemDto == null) {
            return null;
        }
        return LinkSystem.builder()
            .linkSystemId(linkSystemDto.getLinkSystemId())
            .instCode(linkSystemDto.getInstCode())
            .systemName(linkSystemDto.getSystemName())
            .ipAddress(linkSystemDto.getIpAddress())
            .useYn(linkSystemDto.getUseYn())
            .build();
    }
}
