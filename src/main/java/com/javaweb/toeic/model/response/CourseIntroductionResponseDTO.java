package com.javaweb.toeic.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseIntroductionResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String teacherName;
    private String certificateTeacher;
    private String EmailTeacher;
    private List<TopCourseResponseDTO> topCourses;
    private List<ChapterResponseDTO> chapters;
    private List<String> targetOfCourse;
}



