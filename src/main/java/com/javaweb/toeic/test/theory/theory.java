package com.javaweb.toeic.test.theory;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.javaweb.toeic.entity.TheoryEntity;
import com.javaweb.toeic.repository.LessonRepository;
import com.javaweb.toeic.repository.TheoryRepository;
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
public class theory {
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    TheoryRepository theoryRepository;
    @GetMapping("/add/theory/form")
    public ModelAndView addTheory() {
        ModelAndView view = new ModelAndView("web/test/theory/addTheory");
        view.addObject("theoryDTO", new theoryDTO());
        return view;
    }
//    @PostMapping("add/theory")
//    public void add(theoryDTO theoryDTO){
//        Map r = null;
//        try {
//            r = cloudinary.uploader().upload(theoryDTO.getVideo().getBytes(),
//                    ObjectUtils.asMap("resource_type", "auto"));
//            String video = (String) r.get("secure_url");
//            TheoryEntity tEntity = new TheoryEntity();
//            tEntity.setVideo(video);
//            tEntity.setName(theoryDTO.getName());
//            tEntity.setOrderNumber(theoryDTO.getOrderNumber());
//            tEntity.setTheory("");
//            tEntity.setLesson(lessonRepository.findById(theoryDTO.getLessonId()).get());
//            theoryRepository.save(tEntity);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @PostMapping("add/theory")
    public ResponseEntity<Map> add(@RequestParam("video") MultipartFile video,
                                   @RequestParam("name") String name,
                                   @RequestParam("orderNumber") int orderNumber,
                                   @RequestParam("lessonId") Long lessonId,
                                   @RequestParam("theory") MultipartFile theory) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map uploadResult = cloudinary.uploader().upload(video.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String videoUrl = (String) uploadResult.get("secure_url");
            Map uploadResult1 = cloudinary.uploader().upload(theory.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String theoryUrl = (String) uploadResult1.get("secure_url");
            TheoryEntity tEntity = new TheoryEntity();
            tEntity.setVideo(videoUrl);
            String name1 = name.toLowerCase();
            tEntity.setName("Bài giảng về " + name1);
            tEntity.setOrderNumber(orderNumber);
            tEntity.setTheory(theoryUrl);
            tEntity.setLesson(lessonRepository.findById(lessonId).orElse(null));
            theoryRepository.save(tEntity);

            response.put("status", "success");
            response.put("message", "Theory added successfully");
        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }


}
