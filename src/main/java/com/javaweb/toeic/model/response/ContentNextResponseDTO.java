package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.TypeContentOfLesson;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContentNextResponseDTO {
    private Long contentOfLessonNextId;
    private TypeContentOfLesson typeContentOfLesson;
    private String contentOfChapterName;
    private Long contentOfChapterId;
}
