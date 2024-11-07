package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.ExerciseFormatTOEICEntity;
import com.javaweb.toeic.entity.TheoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheoryRepository extends JpaRepository<TheoryEntity, Long> {
    List<TheoryEntity> findByLesson_Id(Long lessonId);
    Optional<TheoryEntity> findFirstByLesson_IdOrderByOrderNumberAsc(Long lessonId);
}
