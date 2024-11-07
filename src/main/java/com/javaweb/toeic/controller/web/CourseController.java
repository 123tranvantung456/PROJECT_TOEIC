package com.javaweb.toeic.controller.web;

import com.javaweb.toeic.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "courseControllerOfWeb")
public class CourseController {
    @Autowired
    private CourseService courseService;  
    @GetMapping(value = "/courses")
    public ModelAndView getActiveCourses() {
        ModelAndView mav = new ModelAndView("web/courses/listCourse");
        mav.addObject("listCourses", courseService.findAllCourseByStatus(true));
        return mav;
    }
    @GetMapping(value = "/courses/{id}/introduction")
    public ModelAndView getCourseIntroduction(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("web/courses/courseIntroduction");
        mav.addObject("courseIntroduction", courseService.getContentIntroduction(id));
        return mav;
    }
    @GetMapping(value = "/courses/{id}/chapters")
    public ModelAndView getContentInCourse(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("web/courses/myCourse");
        modelAndView.addObject("myCourse", courseService.getContentMyCourse(id));
        return modelAndView;
    }
}
