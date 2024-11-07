package com.javaweb.toeic.api.web;

import com.javaweb.toeic.entity.CourseEntity;
import com.javaweb.toeic.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "courseAPIOfWeb")
@RequestMapping(value = "/api/course")
public class CourseAPI {
    @Autowired
    private CourseService courseService;
    @GetMapping
    public List<CourseEntity> findActiveCourses (){
        return null;
    }
}