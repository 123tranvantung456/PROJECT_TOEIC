package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.MultipleChoiceQuestionEntity;
import com.javaweb.toeic.model.response.MultipleChoiceQuestionResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceQuestionConverter {
    @Autowired
    private ModelMapper modelMapper;
    public MultipleChoiceQuestionResponseDTO toMultipleChoiceQuestionResponseDTO
            (MultipleChoiceQuestionEntity multipleChoiceQuestionEntity) {
        return modelMapper.map(multipleChoiceQuestionEntity, MultipleChoiceQuestionResponseDTO.class);
    }
}
