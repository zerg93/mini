package org.study.mini.dto;

import lombok.*;

/**
 * 1. ClassName     : ServiceDto
 * 2. FileName      : ServiceDto.java
 * 3. Package       : org.study.mini.dto
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 4:05
 * 6. Comment       :
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceDto {
    
    private Long serviceId;
    private String serviceCode;
    private String serviceEngName;
    private String serviceKorName;
    private String instCode;
    private String topicCode;
    private String useYn;
}
