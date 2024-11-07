package com.javaweb.toeic.api.web;

import com.javaweb.toeic.exception.DataNotFoundException;
import com.javaweb.toeic.model.response.ContentOfChapterResponseDTO;
import com.javaweb.toeic.model.response.custom.ResponseData;
import com.javaweb.toeic.model.response.custom.ResponseError;
import com.javaweb.toeic.service.ChapterService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "chapterAPIOfWeb")
@RequestMapping(value = "/api/chapter")
public class ChapterAPI {
    private final ChapterService chapterService;
    @GetMapping(value = "/{chapterId}")
    public List<ContentOfChapterResponseDTO> getContentOfChapter(@PathVariable @Min(1) Long chapterId) {
        try {
            return chapterService.getContentInChapter(chapterId);
        }catch (DataNotFoundException e){
            return null;
        }
    }
}
