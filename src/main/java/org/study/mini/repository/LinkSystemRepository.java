package org.study.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.mini.entity.LinkSystem;

import java.util.List;

/**
 * 1. ClassName     : LinkSystemRepository
 * 2. FileName      : LinkSystemRepository.java
 * 3. Package       : org.study.mini.repository
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:31
 * 6. Comment       :
 */
public interface LinkSystemRepository extends JpaRepository<LinkSystem, Long> {
    List<LinkSystem> findAllByUseYn(String useYn);
}
