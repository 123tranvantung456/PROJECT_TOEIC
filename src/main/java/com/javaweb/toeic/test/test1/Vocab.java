package com.javaweb.toeic.test.test1;

import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.entity.VocabularyListEntity;
import com.javaweb.toeic.enums.PartsOfSpeech;
import com.javaweb.toeic.repository.VocabularyListRepository;
import com.javaweb.toeic.repository.VocabularyRepository;
import com.javaweb.toeic.service.StorageService;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@RestController
public class Vocab {
    @Autowired
    private StorageService storageService;
    @Autowired
    private VocabularyRepository vocabularyRepository;
    @Autowired
    private VocabularyListRepository vocabularyListRepository;

    public void addVocabularyS(VocabularyDTO vocabularyDTO) {
        // Tạo đối tượng từ vựng mới
        VocabularyEntity vocabulary = new VocabularyEntity();
        vocabulary.setEnglish(vocabularyDTO.getEnglish());
        vocabulary.setVietnamese(vocabularyDTO.getVietnamese());
        vocabulary.setPronunciation(vocabularyDTO.getPronunciation());
        vocabulary.setExamples(vocabularyDTO.getExamples());
        vocabulary.setDefinition(vocabularyDTO.getDefinition());
        vocabulary.setPartsOfSpeech(vocabularyDTO.getPartsOfSpeech());

        // Lưu file ảnh và thiết lập đường dẫn trong thực thể từ vựng
        if (vocabularyDTO.getImage() != null && !vocabularyDTO.getImage().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String filename = storageService.getStorageFilename(vocabularyDTO.getImage(), uuid.toString());
            storageService.store(vocabularyDTO.getImage(), filename);
            vocabulary.setImage(filename);
        }

        // Thêm từ vựng vào danh sách từ vựng
        VocabularyListEntity vocabularyListEntity = vocabularyListRepository.findById(1L).get();
        vocabularyListEntity.getVocabularyEntities().add(vocabulary);

        // Chỉ lưu danh sách từ vựng (cascade sẽ tự động lưu từ vựng mới)
        vocabularyListRepository.save(vocabularyListEntity);
    }

    @PostMapping("/vocabulary/add")
    public void addVocabularyC(@ModelAttribute VocabularyDTO vocabularyDTO) {
        addVocabularyS(vocabularyDTO);
    }

    @GetMapping("/vocab/")
    public ModelAndView showVocabulary() {
        ModelAndView mav = new ModelAndView("web/test/addVocab");
        mav.addObject("type", PartsOfSpeech.getAllFormats());
        return mav;
    }

    //    @GetMapping("/delete")
//    public void del(){
//        VocabularyListEntity vocabularyListEntity = vocabularyListRepository.findById(3L).get();
//        vocabularyListEntity.setVocabularyEntities(new ArrayList<>());
//        vocabularyListRepository.save(vocabularyListEntity);
//
////        for (long i = 264; i <= 318; i++) {
////            vocabularyRepository.deleteById(i);
////        }
//
//    }
    @GetMapping("/upload")
    public ModelAndView showUploadForm() {
        return new ModelAndView("web/test/file");
    }

    @Autowired
    private WordFileService wordFileService;
    @Autowired
    private WordFileService1 wordFileService1;
    @Autowired
    private DocxReader docxReader;

    @PostMapping("/upload-word")
    public void uploadWordFile(@RequestParam("file") MultipartFile file) {
        try {
            // Trích xuất ảnh từ file Word
            List<ImageData> images = docxReader.extractDocxImages(file);
            List<String> imagePaths = new ArrayList<>();
            String input = wordFileService1.readDocxText(file);
            if (images.size() == 2) {
                // Lưu trữ từng ảnh và lưu đường dẫn vào danh sách
                for (ImageData imageData : images) {
                    String imageId = UUID.randomUUID().toString();
                    String filename = "image_" + imageId + "." + imageData.getExtension();

                    // Lưu ảnh vào hệ thống file
                    storageService.store(imageData.getData(), filename);

                    // Thêm đường dẫn ảnh vào danh sách
                    imagePaths.add(filename);
                }

                String[] entries = input.split("\n\n");
                String word = null;
                int j = 0;
                for (String entry : entries) {
                    String[] header = entry.split("\n");
                    if (header.length < 5) {
                        continue;
                    }
                    if (header[0].equals("")) {
                        for (int i = 0; i < header.length - 1; i++) {
                            header[i] = header[i + 1];
                        }
                    }

                    int w = header[0].indexOf("(");
                    word = header[0].substring(0, w - 1);
                    VocabularyEntity vocabularyEntity = vocabularyRepository
                            .findFirstByEnglishAndVocabularyList_IdOrderByEnglish(word, 1L);
                    if (vocabularyEntity != null && j < imagePaths.size()) {
                        vocabularyEntity.setImage(imagePaths.get(j));
                        vocabularyRepository.save(vocabularyEntity);
                        ++j;
                    }

                }
            }

        }catch (IOException e) {
            throw new RuntimeException("Error processing Word file", e);
        }

    }
}
