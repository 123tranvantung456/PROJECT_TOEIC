package com.javaweb.toeic.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChapterInMyCourseResponseDTO {
   private Long id;
   private String name;
   private Integer completeRate;

}
