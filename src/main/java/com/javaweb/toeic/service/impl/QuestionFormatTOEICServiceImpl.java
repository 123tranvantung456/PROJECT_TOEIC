package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.QuestionFormatTOEICConverter;
import com.javaweb.toeic.entity.QuestionFormatTOEICEntity;
import com.javaweb.toeic.model.response.AbstractQuestionFormatTOEICResponseDTO;
import com.javaweb.toeic.repository.QuestionFormatTOEICRepository;
import com.javaweb.toeic.service.QuestionFormatTOEICService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionFormatTOEICServiceImpl implements QuestionFormatTOEICService {
    @Autowired
    private QuestionFormatTOEICRepository questionFormatTOEICRepository;
    @Autowired
    private QuestionFormatTOEICConverter questionFormatTOEICConverter;
    @Override
    public AbstractQuestionFormatTOEICResponseDTO getQuestionById(Long id) {
        QuestionFormatTOEICEntity AbsQuestionFormatTOEIC = questionFormatTOEICRepository.findById(id).get();
        return questionFormatTOEICConverter.toQuestionFormatTOEICResponseDTO(AbsQuestionFormatTOEIC);
    }
}
