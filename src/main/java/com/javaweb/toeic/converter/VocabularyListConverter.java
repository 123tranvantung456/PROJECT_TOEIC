package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.VocabularyListInLessonEntity;
import com.javaweb.toeic.model.dto.VocabularyListDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VocabularyListConverter {
    @Autowired
    private ModelMapper modelMapper;
    public VocabularyListInLessonEntity toVocabularyListEntity (VocabularyListDTO vocabularyListDTO) {
        return modelMapper.map(vocabularyListDTO, VocabularyListInLessonEntity.class);
    }
}
