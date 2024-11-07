package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.ExerciseFormatGrammarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseFormatGrammarRepository extends JpaRepository<ExerciseFormatGrammarEntity, Long> {
    List<ExerciseFormatGrammarEntity> findByLesson_Id(Long lessonId);
    Optional<ExerciseFormatGrammarEntity> findFirstByLesson_IdOrderByOrderNumberAsc(Long lessonId);
}
