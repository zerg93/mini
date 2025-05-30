package org.study.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.mini.entity.Service;

import java.util.List;

/**
 * 1. ClassName     : ServiceRepository
 * 2. FileName      : ServiceRepository.java
 * 3. Package       : org.study.mini.repository
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 27. 오후 4:11
 * 6. Comment       :
 */
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByUseYn(String useYn);
}
