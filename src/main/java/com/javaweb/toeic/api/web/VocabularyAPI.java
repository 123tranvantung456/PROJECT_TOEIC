package com.javaweb.toeic.api.web;

import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.model.dto.VocabularyDTO;
import com.javaweb.toeic.model.response.VocabularyResponseDTO;
import com.javaweb.toeic.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vocabulary")
public class VocabularyAPI {
    @Autowired
    private VocabularyService vocabularyService;
    @GetMapping(value = "/{id}")
    public VocabularyResponseDTO getVocabulary(@PathVariable Long id) {
        return vocabularyService.getVocabulary(id);
    }
    @GetMapping(value = "/next/{ids}/list/{listId}")
    public Long findVocabNextInStudyFlashcard (@PathVariable List<Long> ids, @PathVariable Long listId) {
        return vocabularyService.findVocabNextInStudyFlashcard(ids, listId);
    }
    @GetMapping(value = "/memorize-vocabulary/{id}")
    public void memorizeVocabulary (@PathVariable Long id) {
        vocabularyService.memoryVocabulary(id);
    }
}
