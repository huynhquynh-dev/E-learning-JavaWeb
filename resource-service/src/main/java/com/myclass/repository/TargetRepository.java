package com.myclass.repository;

import com.myclass.model.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TargetRepository extends JpaRepository<Target, Long> {
}
