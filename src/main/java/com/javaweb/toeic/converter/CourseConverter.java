package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.CourseEntity;
import com.javaweb.toeic.model.response.ChapterInMyCourseResponseDTO;
import com.javaweb.toeic.model.response.CourseIntroductionResponseDTO;
import com.javaweb.toeic.model.response.CourseResponseDTO;
import com.javaweb.toeic.model.response.MyCourseResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class CourseConverter {
    @Autowired
    private ModelMapper modelMapper;
    private String toPriceString(double price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedAmount = formatter.format(price);
        formattedAmount = formattedAmount.replace(",", ".");
        return formattedAmount + "đ";
    }
    public CourseResponseDTO toCourseResponseDTO(CourseEntity courseEntity) {
        CourseResponseDTO courseResponseDTO = modelMapper.map(courseEntity, CourseResponseDTO.class);
        courseResponseDTO.setTeacherName(courseEntity.getTeacher().getName());
        courseResponseDTO.setPrice(toPriceString(courseEntity.getPrice()));
        courseResponseDTO.setStudentNumber(1);
        return courseResponseDTO;
    }

    public MyCourseResponseDTO toMyCourseResponseDTO(String nameCourse, List<ChapterInMyCourseResponseDTO> chapterInMyCourseResponseDTOs) {
        MyCourseResponseDTO result = new MyCourseResponseDTO();
        result.setName(nameCourse);
        result.setChapterResponseDTOS(chapterInMyCourseResponseDTOs);
        return result;
    }

    public CourseIntroductionResponseDTO toCourseIntroductionResponseDTO(CourseEntity courseEntity) {
        return  modelMapper.map(courseEntity, CourseIntroductionResponseDTO.class);
    }
}
