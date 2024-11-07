package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.TypeContentOfLesson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentOfLessonResponseDTO {
    private Long id;
    private String name;
    private Boolean isComplete;
    private TypeContentOfLesson typeContent;

}
