package com.javaweb.toeic.test.exTOE;

import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.entity.compositekey.Exercise_QuestionFormatTOEICPK;
import com.javaweb.toeic.enums.PartFiveQuestionType;
import com.javaweb.toeic.enums.TypeGrammar;
import com.javaweb.toeic.repository.ExerciseFormatTOEICRepository;
import com.javaweb.toeic.repository.Exercise_QuestionFormatTOEICRepository;
import com.javaweb.toeic.repository.QuestionFormatTOEICRepository;
import com.javaweb.toeic.test.test1.WordFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class exP5 {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WordFileService wordFileService;
    @Autowired
    private ExerciseFormatTOEICRepository exerciseFormatTOEICRepository;
    @Autowired
    private QuestionFormatTOEICRepository questionFormatTOEICRepository;
    @Autowired
    private Exercise_QuestionFormatTOEICRepository exercise_questionFormatTOEICRepository;
    @GetMapping(value = "/exTOE/p5/add/")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("web/test/exTOE/p5");
        return view;
    }
    @PostMapping(value = "/exTOE/p5/add/")
    public void add1(@RequestParam("file") MultipartFile file) {
        add(getMultipartFile(file));
    }
    private String getMultipartFile(MultipartFile file){
        try {
            String content = "";
            if (file.getOriginalFilename().endsWith(".doc")) {
                content = wordFileService.readDocFile(file);
            } else if (file.getOriginalFilename().endsWith(".docx")) {
                content = wordFileService.readDocxFile(file);
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void add(String content) {
        String[] str = content.split("\n\n");
        List<PartFiveQuestionEntity> list = new ArrayList<>();
        PartFiveQuestionEntity entity = null;
        for (String str1 : str) {
            String[] str1Temp = str1.split("\n");
            entity = new PartFiveQuestionEntity();
            entity.setQuestionContent(str1Temp[0]);
            System.out.println(str1Temp[1].substring(2));
            System.out.println(str1Temp[1].substring(4));
            entity.setContentAnswerA(str1Temp[1].substring(4));
            entity.setContentAnswerB(str1Temp[2].substring(4));
            entity.setContentAnswerC(str1Temp[3].substring(4));
            entity.setContentAnswerD(str1Temp[4].substring(4));
            entity.setCorrectAnswer(str1Temp[6].charAt(str1Temp[6].length() - 5));
            entity.setPartFiveQuestionType(PartFiveQuestionType.QUESTION_GRAMMAR);
            Set<TypeGrammar> typeGrammarSet = new HashSet<>();
            typeGrammarSet.add(TypeGrammar.COMPARATIVE_STRUCTURE);
            entity.setTypeGrammars(typeGrammarSet);
            String ansP = "";
            for (int i = 7; i < str1Temp.length; i++) {
                if(i == str1Temp.length - 1){
                    ansP += str1Temp[i];
                }
                else ansP += str1Temp[i] + " <br> ";
            }
            entity.setAnswerExplanation(ansP);
            list.add(entity);
        }
        ExerciseFormatTOEICEntity ex = exerciseFormatTOEICRepository.findById(1018L).orElse(null);
        int i = 1;
        for (PartFiveQuestionEntity p : list) {
            QuestionFormatTOEICEntity q5 = questionFormatTOEICRepository.save(p);
            Exercise_QuestionFormatTOEICPK exerciseQuestionFormatTOEICPK = new Exercise_QuestionFormatTOEICPK();
            exerciseQuestionFormatTOEICPK.setQuestionFormatTOEICId(q5.getId());
            exerciseQuestionFormatTOEICPK.setExerciseFormatTOEICId(ex.getId());
            Exercise_QuestionFormatTOIECEntity exerciseQuestionFormatTOIECEntity = new Exercise_QuestionFormatTOIECEntity();
            exerciseQuestionFormatTOIECEntity.setId(exerciseQuestionFormatTOEICPK);
            exerciseQuestionFormatTOIECEntity.setExerciseFormatTOEIC(ex);
            exerciseQuestionFormatTOIECEntity.setQuestionFormatTOEIC(q5);
            exerciseQuestionFormatTOIECEntity.setOrderNumber(i);
            exercise_questionFormatTOEICRepository.save(exerciseQuestionFormatTOIECEntity);
            ++i;

        }
    }
}
