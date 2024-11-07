package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.OrderNumberOfExerciseFormatGrammarResponseDTO;
import com.javaweb.toeic.model.response.OrderNumberOfExerciseFormatTOEICResponseDTO;

import java.util.List;

public interface ExerciseFormatTOEICService {
    List<OrderNumberOfExerciseFormatTOEICResponseDTO> getOrderNumberOfExerciseFormatToeicResponseDTOs(Long exerciseId);

}
