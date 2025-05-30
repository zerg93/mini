package org.study.mini.dto;

import lombok.*;

/**
 * 1. ClassName     : ApiDto
 * 2. FileName      : ApiDto.java
 * 3. Package       : org.study.mini.dto
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:14
 * 6. Comment       : postgres DB의 mini 시퀀스의 api 테이블을 매핑하는 DTO 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiDto {
    // postgres DB의 mini 시퀀스의 api 테이블을 매핑하는 DTO 클래스
    private Long apiId; // 시퀀스 ID
    private String serviceCode;
    private String apiEngName; // API 영문명
    private String apiKorName; // API 한글명
    private String requestUrl; // 요청 URL
    private String responseUrl; // 응답 URL
    private String reverseYn;
    private String useYn; // 사용 여부
}
