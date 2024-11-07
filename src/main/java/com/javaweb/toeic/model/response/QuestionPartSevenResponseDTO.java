package com.javaweb.toeic.model.response;

public class QuestionPartSevenResponseDTO extends AbstractQuestionOfPartThreeToSevenResponseDTO{
    private String questionContent;

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
