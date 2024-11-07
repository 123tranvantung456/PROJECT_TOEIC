package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.VocabularyListInLessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabularyListInLessonRepository extends JpaRepository<VocabularyListInLessonEntity, Long> {
    List<VocabularyListInLessonEntity> findByLesson_IdAndUser_Id(Long lessonId,Long userId);
}
