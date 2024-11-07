package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.ExerciseFormatGrammarEntity;
import com.javaweb.toeic.model.response.ExerciseFormatGrammarResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseFormatGrammarConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ExerciseFormatGrammarResponseDTO toExerciseFormatGrammarResponseDTO(ExerciseFormatGrammarEntity exerciseFormatGrammarEntity) {
        return modelMapper.map(exerciseFormatGrammarEntity, ExerciseFormatGrammarResponseDTO.class);
    }
}
