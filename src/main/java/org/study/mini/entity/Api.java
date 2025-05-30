package org.study.mini.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 1. ClassName     : Api
 * 2. FileName      : Api.java
 * 3. Package       : org.study.mini.entity
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:16
 * 6. Comment       : postgres DB의 mini 시퀀스의 link_system 테이블을 매핑하는 jpa entity 클래스
 */
@Entity
@Table(name = "api", schema = "mini")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api {
    // postgres DB의 mini 시퀀스의 link_system 테이블을 매핑하는 jpa entity 클래스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_id")
    private Long api_id;
    
    @Column(name = "service_code", nullable = false, length = 10)
    private String serviceCode;
    
    @Column(name = "api_eng_name", nullable = false, length = 50)
    private String apiEngName;
    
    @Column(name = "api_kor_name", nullable = false, length = 50)
    private String apiKorName;
    
    @Column(name = "request_url", nullable = false, length = 200)
    private String requestUrl;
    
    @Column(name = "response_url", length = 200)
    private String responseUrl;
    
    @Column(name = "reverse_yn", nullable = false, length = 1)
    private String reverseYn;
    
    @Column(name = "use_yn", nullable = false, length = 1)
    private String useYn = "Y";
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createdAt;
    
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime modifiedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = this.modifiedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
}
