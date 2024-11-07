package com.javaweb.toeic.service;

import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.entity.VocabularyListEntity;
import com.javaweb.toeic.model.dto.VocabularyDTO;
import com.javaweb.toeic.model.dto.VocabularyListDTO;
import com.javaweb.toeic.model.response.VocabularyResponseDTO;

import java.util.List;

public interface VocabularyListService {
    int totalVocabularyInList(Long vocabListId);
    int learnedVocabularyInList(Long vocabListId);
    List<VocabularyResponseDTO> getVocabInList(Long vocabListId);
    VocabularyListEntity addVocabList(VocabularyListDTO vocabListDTO);
    VocabularyEntity createVocabularyInList(VocabularyDTO vocabDTO, Long listId);
}
