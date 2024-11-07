package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.TypeContentOfChapter;

import java.util.List;

public class LessonInMyChapterResponseDTO extends ContentOfChapterResponseDTO{
    private List<ContentOfLessonResponseDTO> contentOfLessonResponseDTOs;

    LessonInMyChapterResponseDTO(){
        this.typeContentOfChapter = TypeContentOfChapter.LESSON;
    }

    public List<ContentOfLessonResponseDTO> getContentOfLessonResponseDTOs() {
        return contentOfLessonResponseDTOs;
    }

    public void setContentOfLessonResponseDTOs(List<ContentOfLessonResponseDTO> contentOfLessonResponseDTOs) {
        this.contentOfLessonResponseDTOs = contentOfLessonResponseDTOs;
    }

}
