package com.javaweb.toeic.test.exGrammar;

import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.entity.compositekey.ExerciseFormatGrammarFillInTheBlankPK;
import com.javaweb.toeic.entity.compositekey.ExerciseFormatGrammarMultipleChoicePK;
import com.javaweb.toeic.repository.*;
import com.javaweb.toeic.test.test1.WordFileService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class exGrammar {
    @Autowired
    private WordFileService1 wordFileService;
    @Autowired
    private ExerciseFormatGrammarMultipleChoiceRepository exerciseFormatGrammarMultipleChoiceRepository;
    @Autowired
    private ExerciseFormatGrammarRepository exerciseFormatGrammarRepository;
    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    @Autowired
    private FillInTheBlankQuestionRepository fillInTheBlankQuestionRepository;
    @Autowired
    private ExerciseFormatGrammarFillInTheBlankRepository exerciseFormatGrammarFillInTheBlankRepository;

    @GetMapping(value = "/exGr")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("/web/test/exGram/add");
        return modelAndView;
    }
    @Transactional
    @PostMapping(value = "/exGrammar/add/")
    public void add(Long id, @RequestParam("file") MultipartFile file){
        try {
            String content = "";
            if (file.getOriginalFilename().endsWith(".doc")) {
                content = wordFileService.readDocFile(file);
            } else if (file.getOriginalFilename().endsWith(".docx")) {
                content = wordFileService.readDocxText(file);
            }
            System.out.println(content);
            add(content);
        } catch (IOException e) {
            System.out.println("err");
        }
    }

    public void add(String content) {
//        List<MultipleChoiceQuestionEntity> mulQ = new ArrayList();
        List<ExerciseFormatGrammarMultipleChoiceEntity> exerciseFormatGrammarMultipleChoiceEntities =
                new ArrayList();
        String[] entries = content.split("\n\n");
        int i = 15;



        ExerciseFormatGrammarEntity e = exerciseFormatGrammarRepository.findById(16L).get();




        for (String entry : entries) {
            String[] entryArr = entry.split("\n");
            if (!entryArr[1].contains("_")){
                if(entryArr.length == 4){
                    FillInTheBlankQuestionEntity fS = null;
                    ExerciseFormatGrammarFillInTheBlankEntity ex = new ExerciseFormatGrammarFillInTheBlankEntity();
                    ex.setOrderNumber(i);
                    FillInTheBlankQuestionEntity f = new FillInTheBlankQuestionEntity();
                    f.setVietnamese(entryArr[0]);
                    f.setInput(entryArr[1].trim() + " _ " + entryArr[2].trim());
                    f.setEnglish(entryArr[3].trim());
                    boolean check = true;
                    List<FillInTheBlankQuestionEntity> exxxx = fillInTheBlankQuestionRepository.findAll();
                    for (FillInTheBlankQuestionEntity ff : fillInTheBlankQuestionRepository.findAll()) {
                        String s =ff.getInput();
                        if((ff.getInput() != null) && ff.getInput().trim().equals(f.getInput().trim())
                               ){
//                            fS = ff;
//                            check = false;
//                            break;
                            check = false;
                        }
                    }
                    if (!check){
                        continue;
                    }
                    if(check){
                        fS = fillInTheBlankQuestionRepository.save(f);
                    }
                    // Tạo khóa chính tổng hợp (EmbeddedId)
                    ExerciseFormatGrammarFillInTheBlankPK id = new ExerciseFormatGrammarFillInTheBlankPK();
                    id.setExerciseFormatGrammarId(e.getId());
                    id.setFillInTheBlankQuestionId(fS.getId());
                    ex.setId(id);

                    ex.setExerciseFormatGrammar(e);
                    ex.setFillInTheBlankQuestion(fS);

                    exerciseFormatGrammarFillInTheBlankRepository.save(ex);

                    ++i;
                }
            }
            else{
                if (entryArr.length == 4) {
                    MultipleChoiceQuestionEntity savedQuestion = null;
                    ExerciseFormatGrammarMultipleChoiceEntity exerciseFormatGrammarMultipleChoiceEntity = new ExerciseFormatGrammarMultipleChoiceEntity();
                    exerciseFormatGrammarMultipleChoiceEntity.setOrderNumber(i);
                    MultipleChoiceQuestionEntity mulQEntity = new MultipleChoiceQuestionEntity();
                    mulQEntity.setVietnamese(entryArr[0]);
                    mulQEntity.setCorrectAnswer(entryArr[2].trim());
                    mulQEntity.setIncorrectAnswer(entryArr[3].trim());
                    mulQEntity.setInput(entryArr[1]);
                    Boolean check = true;
                    for (MultipleChoiceQuestionEntity m : multipleChoiceQuestionRepository.findAll()){
                        if(m.getInput().trim().equals(mulQEntity.getInput().trim()) && m.getCorrectAnswer().trim().equals(mulQEntity.getCorrectAnswer().trim())){
                            savedQuestion = m;
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        savedQuestion = multipleChoiceQuestionRepository.save(mulQEntity);
                    }
                    // Tạo khóa chính tổng hợp (EmbeddedId)
                    ExerciseFormatGrammarMultipleChoicePK id = new ExerciseFormatGrammarMultipleChoicePK();
                    id.setExerciseFormatGrammarId(e.getId());
                    id.setMultipleChoiceQuestionId(savedQuestion.getId());
                    exerciseFormatGrammarMultipleChoiceEntity.setId(id);

                    exerciseFormatGrammarMultipleChoiceEntity.setExerciseFormatGrammar(e);
                    exerciseFormatGrammarMultipleChoiceEntity.setMultipleChoiceQuestion(savedQuestion);
                    exerciseFormatGrammarMultipleChoiceRepository.save(exerciseFormatGrammarMultipleChoiceEntity);
                    i++;
                }
            }
        }
    }
}
