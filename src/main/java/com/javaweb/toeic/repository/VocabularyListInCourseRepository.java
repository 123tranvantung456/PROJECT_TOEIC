package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.VocabularyListInCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VocabularyListInCourseRepository extends JpaRepository<VocabularyListInCourseEntity, Long> {
    List<VocabularyListInCourseEntity> findByChapter_Id(Long chapterId);
    Optional<VocabularyListInCourseEntity> findFirstByOrderNumberGreaterThanAndChapter_IdOrderByOrderNumberAsc
            (Integer orderNumber, Long chapterId);

}
