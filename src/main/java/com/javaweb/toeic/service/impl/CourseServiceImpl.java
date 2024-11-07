package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.CourseConverter;
import com.javaweb.toeic.entity.ChapterEntity;
import com.javaweb.toeic.entity.CourseEntity;
import com.javaweb.toeic.model.response.*;
import com.javaweb.toeic.repository.ChapterRepository;
import com.javaweb.toeic.repository.CourseRepository;
import com.javaweb.toeic.service.ChapterService;
import com.javaweb.toeic.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public MyCourseResponseDTO getContentMyCourse(Long courseId) {
        List<ChapterInMyCourseResponseDTO> chapterInMyCourseResponseDTOs = chapterService.getChapterInCourse(courseId);
        CourseEntity courseEntity = courseRepository.findById(courseId).get();
        String nameCourse = courseEntity.getName();
        return courseConverter.toMyCourseResponseDTO(nameCourse, chapterInMyCourseResponseDTOs);
    }

    @Override
    public List<CourseResponseDTO> findAllCourseByStatus(boolean status) {
        List<CourseEntity> courseEntities = courseRepository.findByStatus(status);
        List<CourseResponseDTO> result = new ArrayList<>();
        for (CourseEntity courseEntity : courseEntities) {
            CourseResponseDTO courseResponseDTO = courseConverter.toCourseResponseDTO(courseEntity);
            result.add(courseResponseDTO);
        }
        return result;
    }

    @Override
    public CourseIntroductionResponseDTO getContentIntroduction(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).get();
        CourseIntroductionResponseDTO result = courseConverter.toCourseIntroductionResponseDTO(courseEntity);
        result.setTopCourses(findAllTopCourse());
        result.setChapters(getChapterOfCourseIntroduction(courseId));
        result.setTargetOfCourse(courseEntity.getTargetOfCourse());
        return result;
    }

    private List<ChapterResponseDTO> getChapterOfCourseIntroduction (Long courseId){
        List<ChapterEntity> chapterEntities = chapterRepository.findByCourseId(courseId);
        List<ChapterResponseDTO> result = new ArrayList<>();
        for (ChapterEntity chapterEntity : chapterEntities) {
            ChapterResponseDTO chapterResponseDTO = new ChapterResponseDTO();
            chapterResponseDTO.setId(chapterEntity.getId());
            chapterResponseDTO.setName(chapterEntity.getName());
            result.add(chapterResponseDTO);
        }
        return result;
    }

    @Override
    public List<TopCourseResponseDTO> findAllTopCourse() {
        List<TopCourseResponseDTO> result = new ArrayList<>();
        List<CourseEntity> courseEntities = courseRepository.findByIdNot(1L);
        for (CourseEntity courseEntity : courseEntities) {
            TopCourseResponseDTO topCourseResponseDTO = new TopCourseResponseDTO();
            topCourseResponseDTO.setId(courseEntity.getId());
            topCourseResponseDTO.setName(courseEntity.getName());
            topCourseResponseDTO.setImage(courseEntity.getImage());
            result.add(topCourseResponseDTO);
        }
        return result;
    }
}
