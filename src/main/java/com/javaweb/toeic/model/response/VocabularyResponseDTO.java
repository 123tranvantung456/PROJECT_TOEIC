package com.javaweb.toeic.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VocabularyResponseDTO {
    private Long id;
    private String english;
    private String vietnamese;
    private String pronunciation;
    private String image;
    private List<String> examples;
    private String definition;
    private String partsOfSpeech;
    private boolean isLearned;

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }
}
