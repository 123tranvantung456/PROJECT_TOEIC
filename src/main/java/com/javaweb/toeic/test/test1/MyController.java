package com.javaweb.toeic.test.test1;
import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.enums.OrderNumberPart;
import com.javaweb.toeic.model.response.CourseResponseDTO;
import com.javaweb.toeic.repository.ExerciseFormatGrammarRepository;
import com.javaweb.toeic.repository.UserRepository;
import com.javaweb.toeic.repository.VocabularyListInCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseFormatGrammarRepository exersiceFormatGrammarRepository;
    @Autowired
    private VocabularyListInCourseRepository vocabularyListInCourseRepository;
    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("aaa", "1");
        return modelAndView;
    }

    @GetMapping("/them")
    public void ok (){
//        UserEntity user = userRepository.findById(1L).get();
//        List<ExerciseFormatGrammarEntity> exerciseFormatGrammarEntities = exersiceFormatGrammarRepository.findAll();
//        user.setExerciseFormatGrammars(exerciseFormatGrammarEntities);
//        userRepository.save(user);
//        UserEntity user = userRepository.findById(1L).get();
//        List<UserEntity> users = new ArrayList<>();
//        users.add(user);
//        ExerciseFormatGrammarEntity exerciseFormatGrammarEntity = exersiceFormatGrammarRepository.findById(2L).get();
//        exerciseFormatGrammarEntity.setUsers(users);
//        exersiceFormatGrammarRepository.save(exerciseFormatGrammarEntity);
        UserEntity user = userRepository.findById(1L).get();
        List<UserEntity> users = new ArrayList<>();
//        users.add(user);
//        VocabularyListInCourseEntity vocabularyListInCourseEntity = vocabularyListInCourseRepository.findById(2L).get();
//        vocabularyListInCourseEntity.s(users);
//        vocabularyListInCourseEntity.save(vocabularyListInCourseEntity);
    }
    @GetMapping(value = "/te")
    public ModelAndView a (){
        ModelAndView m =  new ModelAndView("te");
        List<CourseResponseDTO> courses = new ArrayList<>();
        courses.add(new CourseResponseDTO());
        courses.add(new CourseResponseDTO());
        courses.add(new CourseResponseDTO());
        courses.get(0).setName("aaa");
        courses.get(1).setName("bbb");
        courses.get(2).setName("ccc");
        m.addObject("courses", courses);
        m.addObject("c", courses.get(0));
        return m;
    }

    @GetMapping(value = "test/enum")
    public ModelAndView testEnum(){
        ModelAndView model =  new ModelAndView("enum");
        model.addObject("orderNumberParts", OrderNumberPart.getAllParts());
        model.addObject("question", new Question());
        return model;
    }
    @PostMapping(value = "/question/update")
    public ModelAndView updateQuestion(@ModelAttribute("question") Question question){
        ModelAndView m =  new ModelAndView("enum");
        System.out.println(question);
        return m;
    }
}

