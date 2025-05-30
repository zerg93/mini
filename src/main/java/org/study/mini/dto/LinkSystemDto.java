package org.study.mini.dto;

import lombok.*;

/**
 * 1. ClassName     : LinkSystemDto
 * 2. FileName      : LinkSystemDto.java
 * 3. Package       : org.study.mini.dto
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:07
 * 6. Comment       : postgres DB의 mini 시퀀스의 link_system 테이블을 매핑하는 DTO 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LinkSystemDto {
    // postgres DB의 mini 시퀀스의 link_system 테이블을 매핑하는 DTO 클래스
    private Long linkSystemId; // 시퀀스 ID
    private String systemName;
    private String instCode; // 기관 코드
    private String ipAddress;
    private String useYn; // 사용 여부
}
