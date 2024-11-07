package com.javaweb.toeic.model.response;

public class QuestionPartSixResponseDTO extends AbstractQuestionOfPartThreeToSevenResponseDTO{
    private Integer orderNumber;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}

