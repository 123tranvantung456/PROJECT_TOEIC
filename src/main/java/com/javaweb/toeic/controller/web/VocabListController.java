package com.javaweb.toeic.controller.web;

import com.javaweb.toeic.service.StorageService;
import com.javaweb.toeic.service.VocabularyListService;
import com.javaweb.toeic.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VocabListController {
    @Autowired
    private VocabularyListService vocabularyListService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private VocabularyService vocabularyService;
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    @GetMapping(value = "/chapters/{chapterId}/vocab-list/{listId}/vocab")
    public ModelAndView getVocabInListInCourse(
            @PathVariable Long chapterId, @PathVariable Long listId) {
        ModelAndView mav = new ModelAndView("web/lists/vocabInCourse");
        mav.addObject("totalVocab", vocabularyListService.totalVocabularyInList(listId));
        mav.addObject("learnedVocab", vocabularyListService.learnedVocabularyInList(listId));
        mav.addObject("vocabInList", vocabularyListService.getVocabInList(listId));
//      mav.addObject("next", chapterService.getContentNextOfChapter(chapterId, listId));
        return mav;
    }

    @GetMapping(value = "/chapters/{chapterId}/lesson/{lessonId}/vocab-list/{listId}/vocab")
    public ModelAndView getVocabInListInLesson(@PathVariable Long chapterId, @PathVariable Long listId,
                                               @PathVariable Long lessonId){
        ModelAndView mav = new ModelAndView("web/lessons/lists/vocabInLesson");
        mav.addObject("totalVocab", vocabularyListService.totalVocabularyInList(listId));
        mav.addObject("learnedVocab", vocabularyListService.learnedVocabularyInList(listId));
        mav.addObject("vocabInList", vocabularyListService.getVocabInList(listId));
        return mav;
    }

    @GetMapping(value = "/chapters/{chapterId}/lesson/{lessonId}/vocab-list/{listId}/flashcard")
    public ModelAndView studyVocabByFlashcardInLesson(@PathVariable Long chapterId, @PathVariable Long listId,
                                               @PathVariable Long lessonId){
        ModelAndView mav = new ModelAndView("web/lessons/lists/flashcard");
        mav.addObject("vocabIds", vocabularyService.getIdsVocabularyInList(listId));
        return mav;
    }

    @GetMapping(value = "/chapters/{chapterId}/lesson/{lessonId}/vocab-list/{listId}/flashcard-review")
    public ModelAndView studyVocabByFlashcardReviewInLesson(@PathVariable Long chapterId, @PathVariable Long listId,
                                                      @PathVariable Long lessonId){
        ModelAndView mav = new ModelAndView("web/lessons/lists/flashcard-review");
        mav.addObject("vocabIds", vocabularyService.vocabMemory(listId));
        return mav;
    }

    @GetMapping(value = "/chapters/1/vocab-list/{listId}/flashcard")
    public ModelAndView studyVocabByFlashcard(@PathVariable Long listId) {
        ModelAndView mav = new ModelAndView("web/lists/flashcard");
        mav.addObject("vocabIds", vocabularyService.getIdsVocabularyInList(listId));
        return mav;
    }

    @GetMapping(value = "/chapters/1/vocab-list/{listId}/flashcard-review")
    public ModelAndView studyVocabByFlashcardReview(@PathVariable Long listId) {
        ModelAndView mav = new ModelAndView("web/lists/flashcard-review");
        mav.addObject("vocabIds", vocabularyService.vocabMemory(listId));
        return mav;
    }

    @GetMapping(value = "/vocab-lists")
    public ModelAndView getAllVocabListOfUser(){
        ModelAndView mav = new ModelAndView("web/lists/");
        mav.addObject("lists");
        return mav;
    }
}
