package com.javaweb.toeic.service;

import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.model.dto.VocabularyDTO;
import com.javaweb.toeic.model.response.VocabularyResponseDTO;

import java.util.List;

public interface VocabularyService {
    List<Long> getIdsVocabularyInList(Long listId);
    VocabularyResponseDTO getVocabulary(Long vocabularyId);
    Long findVocabNextInStudyFlashcard(List<Long> vocabularyIds, Long listId);
    void memoryVocabulary(Long id);
    List<Long> vocabMemory(Long listId);
    VocabularyEntity createVocabulary(VocabularyDTO vocabularyDTO);
}
