package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.PartFourQuestionType;

public class QuestionPartFourResponseDTO extends AbstractQuestionOfPartThreeToSevenResponseDTO{
    private PartFourQuestionType partFourQuestionType;
    private Integer OrderNumberInQuestionCluster;
    private QuestionClusterOfPartFourResponseDTO questionClusterOfPartFour;

    public PartFourQuestionType getPartFourQuestionType() {
        return partFourQuestionType;
    }

    public void setPartFourQuestionType(PartFourQuestionType partFourQuestionType) {
        this.partFourQuestionType = partFourQuestionType;
    }

    public Integer getOrderNumberInQuestionCluster() {
        return OrderNumberInQuestionCluster;
    }

    public void setOrderNumberInQuestionCluster(Integer orderNumberInQuestionCluster) {
        OrderNumberInQuestionCluster = orderNumberInQuestionCluster;
    }

    public QuestionClusterOfPartFourResponseDTO getQuestionClusterOfPartFour() {
        return questionClusterOfPartFour;
    }

    public void QuestionClusterOfPartFourResponseDTO(QuestionClusterOfPartFourResponseDTO questionClusterOfPartFour) {
        this.questionClusterOfPartFour = questionClusterOfPartFour;
    }
}
