package com.javaweb.toeic.test.exTOE.exP7;

import com.cloudinary.Cloudinary;
import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.entity.compositekey.Exercise_QuestionFormatTOEICPK;
import com.javaweb.toeic.enums.PartSevenQuestionType;
import com.javaweb.toeic.enums.PartSixQuestionType;
import com.javaweb.toeic.repository.ExerciseFormatTOEICRepository;
import com.javaweb.toeic.repository.Exercise_QuestionFormatTOEICRepository;
import com.javaweb.toeic.repository.QuestionClusterRepository;
import com.javaweb.toeic.repository.QuestionFormatTOEICRepository;
import com.javaweb.toeic.service.StorageService;
import com.javaweb.toeic.test.test1.DocxReader;
import com.javaweb.toeic.test.test1.WordFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class exP7 {
    @Autowired
    private WordFileService wordFileService;
    @Autowired
    private DocxReader docxReader;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ExerciseFormatTOEICRepository exerciseFormatTOEICRepository;
    @Autowired
    private Exercise_QuestionFormatTOEICRepository exercise_QuestionFormatTOEICRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private QuestionFormatTOEICRepository questionFormatTOEICRepository;
    @Autowired
    private QuestionClusterRepository questionClusterRepository;

    @GetMapping("/ExP7/addNoAu")
    public ModelAndView addNoAu() {
        ModelAndView mav = new ModelAndView("web/test/exTOE/p7");
        return mav;
    }
    @Transactional
    @PostMapping("/ExP7/addNoAu")
    public void addNoAu1(@RequestParam("file") MultipartFile file){
        try {
            add(getMul(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMul (MultipartFile file){
        try {
            String content = "";
            if (file.getOriginalFilename().endsWith(".doc")) {
                content = wordFileService.readDocFile(file);
            } else if (file.getOriginalFilename().endsWith(".docx")) {
                content = wordFileService.readDocxFile(file);
            }
            return content;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void add(String content) throws IOException {

        String[] str = content.split("\n\n\n");
        PartSevenQuestionEntity entity;
        QuestionClusterOfPartSevenEntity questionClusterOfPartSeven;
        List<PartSevenQuestionEntity> part7QuestionEntities = new ArrayList<>();
        if (true) {
            for (String str1 : str) {
                String[]  strTemp = str1.split("\n");
                int i = 3;
                String img = "";
                for(; i < strTemp.length; i++) {
                    if(strTemp[i].startsWith("Dịch nghĩa") || strTemp[i].startsWith("Transcript")) {
                        break;
                    }
                    img += strTemp[i] + " <br> ";
                }
                img = img.substring(0, img.length() - 6);
                questionClusterOfPartSeven = new QuestionClusterOfPartSevenEntity();
                questionClusterOfPartSeven.setImage(img);
                int j = i + 1;
                String mean = "";
                for(; j < strTemp.length; j++) {
                    if(strTemp[j].startsWith("Dịch nghĩa") || (strTemp[j].startsWith("<b>Q") && strTemp[j].endsWith(":</b>"))) {
                        break;
                    }
                    mean += strTemp[j] + " <br> ";
                }
                mean = mean.substring(0, mean.length() - 6);
                questionClusterOfPartSeven.setMean(mean);
                QuestionClusterOfPartSevenEntity qS = questionClusterRepository.save(questionClusterOfPartSeven);
                int k = j;
                while(true){
                    if(strTemp[k].contains("A. ") && strTemp[k + 1].contains("B. ")
                    && strTemp[k + 2].contains("C. ")) break;
                    k++;
                }
                entity = new PartSevenQuestionEntity();
                entity.setQuestionContent(strTemp[k - 1]);
                entity.setContentAnswerA(strTemp[k].substring(4));
                entity.setContentAnswerB(strTemp[k + 1].substring(4));
                entity.setContentAnswerC(strTemp[k + 2].substring(4));
                entity.setContentAnswerD(strTemp[k + 3].substring(4));
                entity.setCorrectAnswer(strTemp[k + 5].charAt(strTemp[k + 5].length() - 5));
                String ans = "";
                for (int l = k + 6; l < strTemp.length; l++){
                    if(l == strTemp.length - 1) {
                        ans += strTemp[l];
                        break;
                    }
                    ans += strTemp[l] + " <br> ";
                }
                entity.setAnswerExplanation(ans);
                entity.setPartSevenQuestionType(PartSevenQuestionType.QUESTION_FILL_IN_THE_BLANK);
                entity.setQuestionClusterOfPartSeven(qS);
                entity.setOrderNumberInQuestionCluster(1);
                part7QuestionEntities.add(entity);
            }
        }
            ExerciseFormatTOEICEntity ex = exerciseFormatTOEICRepository.findById(1052L).orElse(null);

            int order = 1;
            for(PartSevenQuestionEntity part7QuestionEntity : part7QuestionEntities){
                Exercise_QuestionFormatTOEICPK exerciseQuestionFormatTOEICPK = new Exercise_QuestionFormatTOEICPK();
                QuestionFormatTOEICEntity q1 = questionFormatTOEICRepository.save(part7QuestionEntity);
                exerciseQuestionFormatTOEICPK.setQuestionFormatTOEICId(q1.getId());
                exerciseQuestionFormatTOEICPK.setExerciseFormatTOEICId(ex.getId());
                Exercise_QuestionFormatTOIECEntity exerciseQuestionFormatTOIECEntity = new Exercise_QuestionFormatTOIECEntity();
                exerciseQuestionFormatTOIECEntity.setId(exerciseQuestionFormatTOEICPK);
                exerciseQuestionFormatTOIECEntity.setExerciseFormatTOEIC(ex);
                exerciseQuestionFormatTOIECEntity.setQuestionFormatTOEIC(q1);
                exerciseQuestionFormatTOIECEntity.setOrderNumber(order);
                exercise_QuestionFormatTOEICRepository.save(exerciseQuestionFormatTOIECEntity);
                ++order;
            }
        }
}
