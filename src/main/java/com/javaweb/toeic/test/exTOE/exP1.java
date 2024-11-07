package com.javaweb.toeic.test.exTOE;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.entity.compositekey.Exercise_QuestionFormatTOEICPK;
import com.javaweb.toeic.enums.PartOneQuestionType;
import com.javaweb.toeic.repository.ExerciseFormatTOEICRepository;
import com.javaweb.toeic.repository.Exercise_QuestionFormatTOEICRepository;
import com.javaweb.toeic.repository.QuestionFormatTOEICRepository;
import com.javaweb.toeic.service.StorageService;
import com.javaweb.toeic.test.test1.DocxReader;
import com.javaweb.toeic.test.test1.ImageData;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Arrays;
import java.util.Comparator;
@Controller
public class exP1 {
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

    @GetMapping("ExP1/addNoAu")
    public ModelAndView addNoAu() {
        ModelAndView mav = new ModelAndView("web/test/exTOE/p1");
        return mav;
    }
    @PostMapping("/ExP1/addNoAu")
    public void addNoAu1(@RequestParam("file")MultipartFile file){
        try {
            add(getMul(file), imgLocal(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> imgLocal(MultipartFile file){
        try {
            List<ImageData> images = docxReader.extractDocxImages(file);
            List<String> imagePaths = new ArrayList<>();
            for (ImageData imageData : images) {
                String imageId = UUID.randomUUID().toString();
                String filename = "image_" + imageId + "." + imageData.getExtension();

                // Lưu ảnh vào hệ thống file
                storageService.store(imageData.getData(), filename);

                // Thêm đường dẫn ảnh vào danh sách
                imagePaths.add(filename);
            }
            return imagePaths;
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

    private void add(String content, List<String> imagePaths) throws IOException {

        List<String> fstr = listFiles("D:\\Dowloads\\audio");

        String[] str = content.split("\n\n");
        PartOneQuestionEntity entity;
        int imgIndex = 0;
        List<PartOneQuestionEntity> partOneQuestionEntities = new ArrayList<>();
        if (imagePaths.size() == str.length) {
            for (String str1 : str) {
                String[] str1Temp = str1.split("\n");
                entity = new PartOneQuestionEntity();
                File file = new File("D:\\Documents\\LapTrinhJavaFull\\toeic\\uploads\\temp\\" + imagePaths.get(imgIndex));
                Map uploadResult = cloudinary.uploader().upload(file.getAbsolutePath(),
                        ObjectUtils.asMap("resource_type", "auto"));
                String videoUrl = (String) uploadResult.get("secure_url");
                entity.setImage(videoUrl);
                //
                entity.setPartOneQuestionType(PartOneQuestionType.DESCRIPTION_PERSON_AND_OBJECT);
                //


                String tran = "";
                int ix = 6;
                if (str1Temp[5].startsWith("(A)")) ix = 5;
                for(; ix <= 9; ix++){
                    if(ix == 9){
                        tran += str1Temp[ix];
                    }
                    else tran += str1Temp[ix] + " <br> ";
                }
                entity.setTranscript(tran);
                entity.setCorrectAnswer(str1Temp[11].charAt(str1Temp[11].length() - 5));

                StringBuilder ans = new StringBuilder();
                if(str1Temp.length <= 16) {
                        for(int i = 12; i <= str1Temp.length - 1; i++){
                            if(i == str1Temp.length - 1){
                                ans.append(str1Temp[i]);
                            }
                            else ans.append(str1Temp[i]).append(" <br> ");
                    }
                }
                else{
                    for(int i = 13; i <= str1Temp.length - 1; i++){
                        if(i == 16){
                            ans.append(str1Temp[i]);
                        }
                        else ans.append(str1Temp[i]).append(" <br> ");
                    }
                }

                entity.setAnswerExplanation(ans.toString());

                File f1 = new File("D:\\Dowloads\\audio\\" + fstr.get(imgIndex));
                Map uploadResult1 = cloudinary.uploader().upload(f1.getAbsolutePath(),
                        ObjectUtils.asMap("resource_type", "auto"));
                String videoUrl1 = (String) uploadResult1.get("secure_url");
                entity.setAudio(videoUrl1);
                ++imgIndex;
                partOneQuestionEntities.add(entity);

            }
            ExerciseFormatTOEICEntity ex = exerciseFormatTOEICRepository.findById(1021L).orElse(null);
            int order = 1;
            for(PartOneQuestionEntity partOneQuestionEntity : partOneQuestionEntities){
                Exercise_QuestionFormatTOEICPK exerciseQuestionFormatTOEICPK = new Exercise_QuestionFormatTOEICPK();
                QuestionFormatTOEICEntity q1 = questionFormatTOEICRepository.save(partOneQuestionEntity);
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
