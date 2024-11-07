package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.MultipleChoiceQuestionResponseDTO;

public interface MultipleChoiceQuestionService {
    MultipleChoiceQuestionResponseDTO getMultipleChoiceQuestionById(Long id);
}
