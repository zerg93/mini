package org.study.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.mini.entity.Inst;

import java.util.List;

public interface InstRepository extends JpaRepository<Inst, Long> {
    List<Inst> findAllByUseYn(String useYn);
    
}