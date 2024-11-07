package com.javaweb.toeic.test.exTOE.exP3;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.entity.compositekey.Exercise_QuestionFormatTOEICPK;
import com.javaweb.toeic.enums.PartThreeQuestionType;
import com.javaweb.toeic.repository.ExerciseFormatTOEICRepository;
import com.javaweb.toeic.repository.Exercise_QuestionFormatTOEICRepository;
import com.javaweb.toeic.repository.QuestionClusterRepository;
import com.javaweb.toeic.repository.QuestionFormatTOEICRepository;
import com.javaweb.toeic.service.StorageService;
import com.javaweb.toeic.test.test1.DocxReader;
import com.javaweb.toeic.test.test1.WordFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class exP3 {
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

    @GetMapping("/ExP4/addNoAu")
    public ModelAndView addNoAu() {
        ModelAndView mav = new ModelAndView("web/test/exTOE/p4");
        return mav;
    }
    @PostMapping("/ExP4/addNoAu")
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

    public static List<String> listFiles(String directoryPath) {
        List<String> fileNames = new ArrayList<>();
        File directory = new File(directoryPath);

        // Kiểm tra xem đường dẫn có phải là thư mục không
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                // Sắp xếp theo thời gian chỉnh sửa cuối cùng
                Arrays.sort(files, Comparator.comparingLong(File::lastModified));

                for (File file : files) {
                    // Thêm tên file vào danh sách nếu nó là một file (không phải thư mục)
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }
            }
        } else {
            System.out.println("Đường dẫn không phải là một thư mục.");
        }

        return fileNames;
    }

    private void add(String content) throws IOException {

        List<String> fstr = listFiles("D:\\Dowloads\\audio");

        String[] str = content.split("\n\n\n");
        PartThreeQuestionEntity entity;
        int imgIndex = 0;
        List<PartThreeQuestionEntity> part3QuestionEntities = new ArrayList<>();
        if (fstr.size() == str.length) {
            for (String str1 : str) {

                String[] str1Temp = str1.split("\n");
                String au = null;
                File f1 = new File("D:\\Dowloads\\audio\\" + fstr.get(imgIndex));
                Map uploadResult1 = cloudinary.uploader().upload(f1.getAbsolutePath(),
                        ObjectUtils.asMap("resource_type", "auto"));
                au = (String) uploadResult1.get("secure_url");
                imgIndex++;
                QuestionClusterOfPartThreeEntity questionClusterOfPartThreeEntity = new QuestionClusterOfPartThreeEntity();
                questionClusterOfPartThreeEntity.setAudio(au);
                String tran = "";
                int i = 10;
                for (; true; i++){
                    if(str1Temp[i].contains("Dịch nghĩa")) break;
                    tran += str1Temp[i] + " <br> ";
                }
                tran = tran.substring(0, tran.length() - 6);
                questionClusterOfPartThreeEntity.setTranscript(tran);
                String mean = "";
                int j = i + 1;
                for(; true; j++){
                    if(str1Temp[j].startsWith("<b>Q")) break;
                    mean += str1Temp[j] + " <br> ";
                }
                mean = mean.substring(0, mean.length() - 6);


                questionClusterOfPartThreeEntity.setConversationTopic(null);


                questionClusterOfPartThreeEntity.setMean(mean);

                QuestionClusterOfPartThreeEntity questionSaved = questionClusterRepository.save(questionClusterOfPartThreeEntity);
                entity = new PartThreeQuestionEntity();
                entity.setOrderNumberInQuestionCluster(1);
                entity.setPartThreeQuestionType(PartThreeQuestionType.QUESTION_IMPLICATION);
                entity.setContentAnswerA(str1Temp[j + 2].substring(4));
                entity.setContentAnswerB(str1Temp[j + 3].substring(4));
                entity.setContentAnswerC(str1Temp[j + 4].substring(4));
                entity.setContentAnswerD(str1Temp[j + 5].substring(4));
                entity.setCorrectAnswer(str1Temp[j + 7].charAt(str1Temp[j + 7].length() - 5));
                String ansEx = "";
                int k = j + 7 + 1;
                for (; k < str1Temp.length; k++){
                    if (k == str1Temp.length - 1) {
                        ansEx += str1Temp[k];
                    }
                    ansEx += str1Temp[k] + " <br> ";
                }
                entity.setAnswerExplanation(ansEx);
                entity.setQuestionContent(str1Temp[j + 1]);
                entity.setQuestionClusterOfPartThree(questionSaved);
                part3QuestionEntities.add(entity);
            }
            ExerciseFormatTOEICEntity ex = exerciseFormatTOEICRepository.findById(1039L).orElse(null);

            int order = 1;
            for(PartThreeQuestionEntity part3QuestionEntity : part3QuestionEntities){
                Exercise_QuestionFormatTOEICPK exerciseQuestionFormatTOEICPK = new Exercise_QuestionFormatTOEICPK();
                QuestionFormatTOEICEntity q1 = questionFormatTOEICRepository.save(part3QuestionEntity);
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
}
