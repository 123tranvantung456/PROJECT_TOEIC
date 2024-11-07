package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.FillInTheBlankQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FillInTheBlankQuestionRepository extends JpaRepository<FillInTheBlankQuestionEntity, Long> {
//    List<FillInTheBlankQuestionEntity> findByExerciseFormatGrammarFillInTheBlanks_ExerciseFormatGrammar_Id(Long exerciseId);
}
