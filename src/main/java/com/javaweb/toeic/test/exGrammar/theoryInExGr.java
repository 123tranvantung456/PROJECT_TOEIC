package com.javaweb.toeic.test.exGrammar;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.javaweb.toeic.entity.ExerciseFormatGrammarEntity;
import com.javaweb.toeic.entity.TheoryEntity;
import com.javaweb.toeic.repository.ExerciseFormatGrammarRepository;
import com.javaweb.toeic.repository.LessonRepository;
import com.javaweb.toeic.repository.TheoryRepository;
import com.javaweb.toeic.test.theory.theoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class theoryInExGr {
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    TheoryRepository theoryRepository;
    @Autowired
    private ExerciseFormatGrammarRepository exerciseFormatGrammarRepository;

    @GetMapping("/ExGr/add/theory/form")
    public ModelAndView addTheory() {
        ModelAndView view = new ModelAndView("web/test/exGram/theory");
        view.addObject("theoryDTO", new theoryDTO());
        return view;
    }

    @PostMapping("/ExGr/theory/add")
    public ResponseEntity<Map> add(@RequestParam("file") MultipartFile file
                                   , @RequestParam("exId") Long exId) {
        Map<String, Object> response = new HashMap<>();
        ExerciseFormatGrammarEntity ex = exerciseFormatGrammarRepository.findById(exId).orElse(null);
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String thUrl = (String) uploadResult.get("secure_url");
            ex.setTheory(thUrl);
            exerciseFormatGrammarRepository.save(ex);
            response.put("status", "success");
            response.put("message", "Theory added successfully");
        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
