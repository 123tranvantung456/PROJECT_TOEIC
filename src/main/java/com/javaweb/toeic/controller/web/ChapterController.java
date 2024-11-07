package com.javaweb.toeic.controller.web;

import com.javaweb.toeic.service.ChapterService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "chapterControllerOfWeb")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping(value = "/chapters/{id}/")
    public ModelAndView getLessonAndVocabListInChapter(@PathVariable @Min(1) Long id) {
        ModelAndView mav = new ModelAndView("web/chapters/contentOfChapter");
        mav.addObject("contentOfChapters", chapterService.getContentInChapter(id));
        mav.addObject("currentChapter", chapterService.getChapterById(id));
        mav.addObject("allChapter", chapterService.getChapter(id));
        return mav;
    }
}
