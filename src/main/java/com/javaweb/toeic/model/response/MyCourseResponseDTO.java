package com.javaweb.toeic.model.response;

import java.util.List;

public class MyCourseResponseDTO {
    String name;
    List<ChapterInMyCourseResponseDTO> chapterResponseDTOS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChapterInMyCourseResponseDTO> getChapterResponseDTOS() {
        return chapterResponseDTOS;
    }

    public void setChapterResponseDTOS(List<ChapterInMyCourseResponseDTO> chapterResponseDTOS) {
        this.chapterResponseDTOS = chapterResponseDTOS;
    }
}
