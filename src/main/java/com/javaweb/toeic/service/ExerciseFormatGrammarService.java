package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.ExerciseFormatGrammarResponseDTO;
import com.javaweb.toeic.model.response.OrderNumberOfExerciseFormatGrammarResponseDTO;

import java.util.List;

public interface ExerciseFormatGrammarService {
    List<OrderNumberOfExerciseFormatGrammarResponseDTO> getOrderNumberInExerciseFormatGrammar(Long exerciseId);
    ExerciseFormatGrammarResponseDTO getExerciseFormatGrammarCurrent(Long exerciseId);
}
