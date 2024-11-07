package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.CourseIntroductionResponseDTO;
import com.javaweb.toeic.model.response.CourseResponseDTO;
import com.javaweb.toeic.model.response.MyCourseResponseDTO;
import com.javaweb.toeic.model.response.TopCourseResponseDTO;

import java.util.List;

public interface CourseService {
    MyCourseResponseDTO getContentMyCourse(Long courseId);
    List<CourseResponseDTO> findAllCourseByStatus(boolean status);
    CourseIntroductionResponseDTO getContentIntroduction(Long courseId);
    List<TopCourseResponseDTO> findAllTopCourse();
}
