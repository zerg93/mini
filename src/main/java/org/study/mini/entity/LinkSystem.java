package org.study.mini.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 1. ClassName     : LinkSystem
 * 2. FileName      : LinkSystem.java
 * 3. Package       : org.study.mini.entity
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:10
 * 6. Comment       : postgres DB의 mini 시퀀스의 link_system 테이블을 매핑하는 jpa entity 클래스
 */
@Entity
@Table(name = "link_system", schema = "mini")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkSystem {
    // postgres DB의 mini 시퀀스의 link_system 테이블을 매핑하는 jpa entity 클래스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_system_id")
    private Long linkSystemId;
    
    @Column(name = "inst_code", nullable = false, length = 10)
    private String instCode;
    
    @Column(name = "system_name", nullable = false, length = 50)
    private String systemName;
    
    @Column(name = "ip_address", nullable = false, length = 50)
    private String ipAddress;
    
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
