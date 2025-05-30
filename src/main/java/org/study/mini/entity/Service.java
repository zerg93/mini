package org.study.mini.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 1. ClassName     : Service
 * 2. FileName      : Service.java
 * 3. Package       : org.study.mini.entity
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 4:06
 * 6. Comment       :
 */
@Entity
@Table(name = "service", schema = "mini")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_code", nullable = false, unique = true, length = 10)
    private String serviceCode;
    
    @Column(name = "service_eng_name", nullable = false, length = 50)
    private String serviceEngName;
    
    @Column(name = "service_kor_name", nullable = false, length = 50)
    private String serviceKorName;
    
    @Column(name = "inst_code", nullable = false, length = 10)
    private String instCode;
    
    @Column(name = "topic_code", nullable = false, length = 10)
    private String topicCode;

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
