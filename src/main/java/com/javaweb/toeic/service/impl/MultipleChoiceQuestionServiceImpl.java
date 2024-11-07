package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.MultipleChoiceQuestionConverter;
import com.javaweb.toeic.entity.MultipleChoiceQuestionEntity;
import com.javaweb.toeic.model.response.MultipleChoiceQuestionResponseDTO;
import com.javaweb.toeic.repository.MultipleChoiceQuestionRepository;
import com.javaweb.toeic.service.MultipleChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultipleChoiceQuestionServiceImpl implements MultipleChoiceQuestionService {
    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    @Autowired
    private MultipleChoiceQuestionConverter multipleChoiceQuestionConverter;
    @Override
    public MultipleChoiceQuestionResponseDTO getMultipleChoiceQuestionById(Long id) {
         MultipleChoiceQuestionEntity multipleChoiceQuestionEntity =
                 multipleChoiceQuestionRepository.findById(id).get();
        return multipleChoiceQuestionConverter.toMultipleChoiceQuestionResponseDTO(multipleChoiceQuestionEntity);
    }
}
