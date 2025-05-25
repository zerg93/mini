package org.study.mini.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 1. ClassName     : Inst
 * 2. FileName      : Inst.java
 * 3. Package       : org.study.mini.entity
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 25. 오후 9:21
 * 6. Comment       :
 */
@Entity
@Table(name = "inst", schema = "mini")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inst {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inst_id")
    private Long instId;
    
    @Column(name = "inst_name", nullable = false, unique = true, length = 50)
    private String instName;
    
    @Column(name = "inst_code", nullable = false, unique = true, length = 10)
    private String instCode;
    
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