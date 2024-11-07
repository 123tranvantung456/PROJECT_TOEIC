package com.javaweb.toeic.api.web;

import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.entity.VocabularyListEntity;
import com.javaweb.toeic.model.dto.VocabularyDTO;
import com.javaweb.toeic.model.dto.VocabularyListDTO;
import com.javaweb.toeic.service.VocabularyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/vocab-list")
public class VocabularyListAPI {

    @Autowired
    private VocabularyListService vocabularyListService;

    @PostMapping
    public ResponseEntity<VocabularyListEntity> AddVocabulary(@RequestBody VocabularyListDTO vocabularyListDTO) {
            VocabularyListEntity createdVocabList = vocabularyListService.addVocabList(vocabularyListDTO);
        return new ResponseEntity<>(createdVocabList, HttpStatus.CREATED);
    }
    @PostMapping("/add-vocab")
    public ResponseEntity<VocabularyEntity> AddOrUpdateVocabularyInList(@ModelAttribute VocabularyDTO vocabularyDTO, @RequestParam Long listId) {
        return new ResponseEntity<>(vocabularyListService.createVocabularyInList(vocabularyDTO, listId), HttpStatus.CREATED);
    }
}
