package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.ExerciseFormatGrammarFillInTheBlankEntity;
import com.javaweb.toeic.entity.compositekey.ExerciseFormatGrammarFillInTheBlankPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseFormatGrammarFillInTheBlankRepository extends JpaRepository<ExerciseFormatGrammarFillInTheBlankEntity, ExerciseFormatGrammarFillInTheBlankPK> {
    List<ExerciseFormatGrammarFillInTheBlankEntity> findByExerciseFormatGrammar_Id(Long exerciseId);
}
