package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.AbstractQuestionFormatTOEICResponseDTO;

public interface QuestionFormatTOEICService {
    public AbstractQuestionFormatTOEICResponseDTO getQuestionById(Long id);
}
