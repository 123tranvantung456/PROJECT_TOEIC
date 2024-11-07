package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.VocabularyListInCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyListNotInCourseRepository extends JpaRepository<VocabularyListInCourseEntity, Long> {

}
