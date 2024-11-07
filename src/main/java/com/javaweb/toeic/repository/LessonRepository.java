package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    List<LessonEntity> findByChapter_Id(Long chapterId);
    Optional<LessonEntity> findFirstByOrderNumberGreaterThanAndChapter_IdOrderByOrderNumberAsc
            (Integer orderNumberCurrent, Long chapterId);
}
