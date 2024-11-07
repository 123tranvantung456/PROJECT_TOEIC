package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.ChapterResponseDTO;
import com.javaweb.toeic.model.response.ChapterInMyCourseResponseDTO;
import com.javaweb.toeic.model.response.ContentNextResponseDTO;
import com.javaweb.toeic.model.response.ContentOfChapterResponseDTO;

import java.util.List;

public interface ChapterService {
    ChapterInMyCourseResponseDTO getChapterById(Long chapterId);
    List<ChapterInMyCourseResponseDTO> getChapterInCourse(Long courseId);
    List<ContentOfChapterResponseDTO> getContentInChapter(Long chapterId);
    List<ChapterResponseDTO> getChapter(Long chapterId);
    ContentNextResponseDTO getContentNextOfChapter(Long chapter, Long lessonId);
}
