package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.ExerciseFormatGrammarFillInTheBlankEntity;
import com.javaweb.toeic.entity.ExerciseFormatGrammarMultipleChoiceEntity;
import com.javaweb.toeic.entity.MultipleChoiceQuestionEntity;
import com.javaweb.toeic.entity.compositekey.ExerciseFormatGrammarMultipleChoicePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseFormatGrammarMultipleChoiceRepository extends JpaRepository<ExerciseFormatGrammarMultipleChoiceEntity, ExerciseFormatGrammarMultipleChoicePK> {
    List<ExerciseFormatGrammarMultipleChoiceEntity> findByExerciseFormatGrammar_Id(Long exerciseId);
}
