package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.ContentNextResponseDTO;
import com.javaweb.toeic.model.response.ContentOfLessonResponseDTO;

import java.util.List;

public interface LessonService {
    List<ContentOfLessonResponseDTO> getContentOfLesson(Long lessonId);
    ContentNextResponseDTO getFirstContentOfLesson(Long lessonId);
}
