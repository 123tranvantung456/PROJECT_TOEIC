package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.MultipleChoiceQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestionEntity, Long>{
//    List<MultipleChoiceQuestionEntity> findByExerciseFormatGrammarMultipleChoices_ExerciseFormatGrammar_Id (Long exerciseId);
}
