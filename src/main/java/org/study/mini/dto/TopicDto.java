package org.study.mini.dto;

import lombok.*;

/**
 * 1. ClassName     : TopicDto
 * 2. FileName      : TopicDto.java
 * 3. Package       : org.study.mini.dto
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 3:41
 * 6. Comment       :
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TopicDto {
    private Long topicId;
    private String topicCode;
    private String useYn;
}
