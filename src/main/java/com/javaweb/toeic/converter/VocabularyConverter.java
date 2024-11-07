package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.entity.VocabularyListEntity;
import com.javaweb.toeic.model.dto.VocabularyDTO;
import com.javaweb.toeic.model.response.VocabularyResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VocabularyConverter {
    @Autowired
    private ModelMapper modelMapper;
    public VocabularyResponseDTO toVocabularyResponseDTO(VocabularyEntity vocabulary) {
        VocabularyResponseDTO vocabularyResponseDTO = modelMapper.map(vocabulary, VocabularyResponseDTO.class);
        String partsOfSpeech = null;
        if(vocabularyResponseDTO.getPartsOfSpeech() != null) partsOfSpeech =
                "(" +vocabulary.getPartsOfSpeech().stream().map(it -> it.getName().toString()).collect(Collectors.joining(", ")) + ")";
        vocabularyResponseDTO.setPartsOfSpeech(partsOfSpeech);
        if(vocabularyResponseDTO.getPronunciation() != null) vocabularyResponseDTO
                .setPronunciation("/"+vocabulary.getPronunciation()+"/");
            return vocabularyResponseDTO;
    }
    public VocabularyEntity toVocabularyEntity (VocabularyDTO vocabularyDTO) {
        return modelMapper.map(vocabularyDTO, VocabularyEntity.class);
    }
}
