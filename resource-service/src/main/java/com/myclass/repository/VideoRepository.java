package com.myclass.repository;

import com.myclass.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VideoRepository extends JpaRepository<Video, Long> {
}
