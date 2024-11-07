package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.ExerciseFormatTOEICEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseFormatTOEICRepository extends JpaRepository<ExerciseFormatTOEICEntity, Long> {
    List<ExerciseFormatTOEICEntity> findByLesson_Id(Long lessonId);
    Optional<ExerciseFormatTOEICEntity> findFirstByLesson_IdOrderByOrderNumberAsc(Long lessonId);

}
