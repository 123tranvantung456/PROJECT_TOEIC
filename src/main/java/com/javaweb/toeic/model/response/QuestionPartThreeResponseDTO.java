package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.PartThreeQuestionType;

public class QuestionPartThreeResponseDTO extends AbstractQuestionOfPartThreeToSevenResponseDTO {
    private PartThreeQuestionType partThreeQuestionType;
    private Integer OrderNumberInQuestionCluster;
    private String questionContent;
    private String image;

    public PartThreeQuestionType getPartThreeQuestionType() {
        return partThreeQuestionType;
    }

    public void setPartThreeQuestionType(PartThreeQuestionType partThreeQuestionType) {
        this.partThreeQuestionType = partThreeQuestionType;
    }

    public Integer getOrderNumberInQuestionCluster() {
        return OrderNumberInQuestionCluster;
    }

    public void setOrderNumberInQuestionCluster(Integer orderNumberInQuestionCluster) {
        OrderNumberInQuestionCluster = orderNumberInQuestionCluster;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

}
