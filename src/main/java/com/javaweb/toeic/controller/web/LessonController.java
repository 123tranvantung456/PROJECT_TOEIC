package com.javaweb.toeic.controller.web;

import com.javaweb.toeic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "lessonControllerOfWeb")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private TheoryService theoryService;
    @Autowired
    private ExerciseFormatGrammarService exerciseFormatGrammarService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ExerciseFormatTOEICService exerciseFormatTOEICService;
    @GetMapping(value = "/chapters/{chapterId}/lesson/{lessonId}/theory/{theoryId}")
    public ModelAndView getVideoAndTheoryInLesson(
            @PathVariable Long chapterId, @PathVariable Long lessonId, @PathVariable Long theoryId) {
        ModelAndView mav = new ModelAndView("web/lessons/theory");
        mav.addObject("contentOfLessons", lessonService.getContentOfLesson(lessonId));
        mav.addObject("theoryCurrent", theoryService.getTheoryCurrent(theoryId));
        mav.addObject("next", chapterService.getContentNextOfChapter(chapterId, lessonId));
        return mav;
    }

    @GetMapping(value = "/chapters/{chapterId}/lesson/{lessonId}/exercise-grammar/{exerciseId}")
    public ModelAndView getExerciseFormatGrammarInLesson(
            @PathVariable Long chapterId, @PathVariable Long lessonId, @PathVariable Long exerciseId) {
        ModelAndView mav = new ModelAndView("web/lessons/exerciseFormatGrammar");
        mav.addObject("contentOfLessons", lessonService.getContentOfLesson(lessonId));
        mav.addObject("exciseGrammarCurrent", exerciseFormatGrammarService.getExerciseFormatGrammarCurrent(exerciseId));
        mav.addObject("orderNumbers",exerciseFormatGrammarService.getOrderNumberInExerciseFormatGrammar(exerciseId));
        mav.addObject("next", chapterService.getContentNextOfChapter(chapterId, lessonId));
        return mav;
    }

    @GetMapping(value = "/chapters/{chapterId}/lesson/{lessonId}/exercise-toeic/{exerciseId}")
    public ModelAndView getExerciseFormatTOIECInLesson(
            @PathVariable Long chapterId, @PathVariable Long lessonId, @PathVariable Long exerciseId) {
        ModelAndView mav = new ModelAndView("web/lessons/exerciseFormatTOEIC");
        mav.addObject("contentOfLessons", lessonService.getContentOfLesson(lessonId));
        mav.addObject("orderNumbers",exerciseFormatTOEICService.getOrderNumberOfExerciseFormatToeicResponseDTOs(exerciseId));
        mav.addObject("next", chapterService.getContentNextOfChapter(chapterId, lessonId));
        mav.addObject("exerciseTOECICId", exerciseId);
        return mav;
    }

    @GetMapping(value = "/chapters/{chapterId}/lesson/{lessonId}/updating")
    public ModelAndView updatingView(
            @PathVariable Long chapterId, @PathVariable Long lessonId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("contentOfLessons", lessonService.getContentOfLesson(lessonId));
        mav.addObject("next", chapterService.getContentNextOfChapter(chapterId, lessonId));
        return mav;
    }
}

