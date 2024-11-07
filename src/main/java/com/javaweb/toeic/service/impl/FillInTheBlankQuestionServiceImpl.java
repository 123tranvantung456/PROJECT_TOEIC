package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.FillInTheBlankQuestionConverter;
import com.javaweb.toeic.entity.FillInTheBlankQuestionEntity;
import com.javaweb.toeic.exception.DataNotFoundException;
import com.javaweb.toeic.model.response.FillInTheBlankQuestionResponseDTO;
import com.javaweb.toeic.repository.FillInTheBlankQuestionRepository;
import com.javaweb.toeic.service.FillInTheBlankQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillInTheBlankQuestionServiceImpl implements FillInTheBlankQuestionService {
    @Autowired
    private FillInTheBlankQuestionRepository fillInTheBlankQuestionRepository;
    @Autowired
    private FillInTheBlankQuestionConverter fillInTheBlankQuestionConverter;
    @Override
    public FillInTheBlankQuestionResponseDTO getFillInTheBlankQuestionById(Long id) {
        FillInTheBlankQuestionEntity fillInTheBlankQuestionEntity =
                fillInTheBlankQuestionRepository.findById(id).orElseThrow(
                        () -> new DataNotFoundException("Question not found")
                );
        return fillInTheBlankQuestionConverter.toFillInTheBlankQuestionResponseDTO(fillInTheBlankQuestionEntity);
    }
}
