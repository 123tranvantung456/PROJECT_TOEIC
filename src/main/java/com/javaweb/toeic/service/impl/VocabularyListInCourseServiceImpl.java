package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.repository.VocabularyRepository;
import com.javaweb.toeic.service.VocabularyListInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VocabularyListInCourseServiceImpl implements VocabularyListInCourseService {
    @Autowired
    private VocabularyRepository vocabularyRepository;
    @Override
    public boolean hasVocabulary(Long vocabListId) {
        return vocabularyRepository.existsByVocabularyList_Id(vocabListId);
    }

}
