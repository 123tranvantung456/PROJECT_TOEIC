package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
    List<ChapterEntity> findByCourseIdOrderByOrderNumber(Long courseId);
    List<ChapterEntity> findByCourseId(Long courseId);
}
