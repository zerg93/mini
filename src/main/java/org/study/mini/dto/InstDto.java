package org.study.mini.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InstDto {
    private Long instId;
    private String instName;
    private String instCode;
    private String useYn;
}