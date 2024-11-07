package com.javaweb.toeic.test.test1;

import com.javaweb.toeic.enums.PartsOfSpeech;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class VocabularyDTO {

    private String english;
    private String vietnamese;
    private String pronunciation;
    private MultipartFile image; // Thay đổi từ String sang MultipartFile
    private List<String> examples;
    private String definition;
    private List<PartsOfSpeech> partsOfSpeech;

    // Getters and Setters

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<PartsOfSpeech> getPartsOfSpeech() {
        return partsOfSpeech;
    }

    public void setPartsOfSpeech(List<PartsOfSpeech> partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }
}
