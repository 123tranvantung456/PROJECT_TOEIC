package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.FillInTheBlankQuestionEntity;
import com.javaweb.toeic.model.response.FillInTheBlankQuestionResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FillInTheBlankQuestionConverter {
    @Autowired
    private ModelMapper modelMapper;
    public FillInTheBlankQuestionResponseDTO toFillInTheBlankQuestionResponseDTO
            (FillInTheBlankQuestionEntity fillInTheBlankQuestionEntity){
        return modelMapper.map(fillInTheBlankQuestionEntity, FillInTheBlankQuestionResponseDTO.class);
    }
}
