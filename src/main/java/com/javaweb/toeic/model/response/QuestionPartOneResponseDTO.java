package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.PartOneQuestionType;

public class QuestionPartOneResponseDTO extends AbstractQuestionOfPartOneAndTwoResponseDTO{
    private PartOneQuestionType partOneQuestionType;
    private String image;

    public PartOneQuestionType getPartOneQuestionType() {
        return partOneQuestionType;
    }

    public void setPartOneQuestionType(PartOneQuestionType partOneQuestionType) {
        this.partOneQuestionType = partOneQuestionType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
