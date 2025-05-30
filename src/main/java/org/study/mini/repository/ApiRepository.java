package org.study.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.mini.entity.Api;

import java.util.List;

/**
 * 1. ClassName     : ApiRepository
 * 2. FileName      : ApiRepository.java
 * 3. Package       : org.study.mini.repository
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 5:30
 * 6. Comment       :
 */
public interface ApiRepository extends JpaRepository<Api, Long> {
    List<Api> findAllByUseYn(String useYn);
}
