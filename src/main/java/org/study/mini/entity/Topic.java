package org.study.mini.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 1. ClassName     : Topic
 * 2. FileName      : Topic.java
 * 3. Package       : org.study.mini.entity
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 3:43
 * 6. Comment       :
 */
@Entity
@Table(name = "topic", schema = "mini")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;
    
    @Column(name = "topic_code", nullable = false, unique = true, length = 10)
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
