package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.VocabularyConverter;
import com.javaweb.toeic.converter.VocabularyListConverter;
import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.entity.VocabularyListEntity;
import com.javaweb.toeic.entity.VocabularyListInLessonEntity;
import com.javaweb.toeic.model.dto.VocabularyDTO;
import com.javaweb.toeic.model.dto.VocabularyListDTO;
import com.javaweb.toeic.model.response.VocabularyResponseDTO;
import com.javaweb.toeic.repository.LessonRepository;
import com.javaweb.toeic.repository.UserRepository;
import com.javaweb.toeic.repository.VocabularyListRepository;
import com.javaweb.toeic.repository.VocabularyRepository;
import com.javaweb.toeic.service.VocabularyListService;
import com.javaweb.toeic.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VocabularyListServiceImpl implements VocabularyListService {
    @Autowired
    private VocabularyRepository vocabularyRepository;
    @Autowired
    private VocabularyConverter vocabularyConverter;
    @Autowired
    private VocabularyListConverter vocabularyListConverter;
    @Autowired
    private VocabularyListRepository vocabularyListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private VocabularyService vocabularyService;

    @Override
    public int totalVocabularyInList(Long vocabListId) {
        return vocabularyRepository.countByVocabularyList_Id(vocabListId);
    }

    @Override
    public int learnedVocabularyInList(Long vocabListId) {
        return vocabularyRepository.countByUsers_IdAndVocabularyList_Id(1L, vocabListId);
    }

    @Override
    public List<VocabularyResponseDTO> getVocabInList(Long vocabListId) {
        List<VocabularyEntity> vocabInList = vocabularyRepository.findByVocabularyList_IdOrderByEnglish(vocabListId);
        List<VocabularyResponseDTO> result = new ArrayList<>();
        for (VocabularyEntity vocabularyEntity : vocabInList) {
            VocabularyResponseDTO vocabularyResponseDTO = vocabularyConverter.toVocabularyResponseDTO(vocabularyEntity);
            vocabularyResponseDTO.setLearned(vocabularyRepository.existsByIdAndUsers_Id(vocabularyEntity.getId(), 1L));
            result.add(vocabularyResponseDTO);
        }
        return result;
    }

    @Override
    public VocabularyListEntity addVocabList(VocabularyListDTO vocabListDTO) {
        VocabularyListInLessonEntity vocabularyListInLessonEntity = vocabularyListConverter.toVocabularyListEntity(vocabListDTO);
        vocabularyListInLessonEntity.setUser(userRepository.findById(vocabListDTO.getUserId()).get());
        vocabularyListInLessonEntity.setLesson(lessonRepository.findById(vocabListDTO.getLessonId()).get());
        vocabularyListInLessonEntity.setOrderNumber(999);
        if(vocabularyListInLessonEntity.getDescription() == "") vocabularyListInLessonEntity.setDescription(null);
        return vocabularyListRepository.save(vocabularyListInLessonEntity);
    }

    @Transactional
    @Override
    public VocabularyEntity createVocabularyInList(VocabularyDTO vocabDTO, Long listId) {
        VocabularyEntity vocabularyEntity = vocabularyService.createVocabulary(vocabDTO);
        VocabularyListEntity vocabularyListEntity = vocabularyListRepository.findById(listId).get();
        vocabularyListEntity.getVocabularyEntities().add(vocabularyEntity);
        return vocabularyEntity;
    }
}
