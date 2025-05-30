package org.study.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.mini.entity.Topic;

import java.util.List;

/**
 * 1. ClassName     : TopicRepository
 * 2. FileName      : TopicRepository.java
 * 3. Package       : org.study.mini.repository
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 3:48
 * 6. Comment       :
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {
    // Topic 엔티티를 사용하여 필요한 쿼리 메서드를 정의할 수 있습니다.
    List<Topic> findAllByUseYn(String useYn);
}
