package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.TypeContentOfExerciseFormatTOEIC;

public class OrderNumberOfExerciseFormatTOEICResponseDTO {
    private Integer orderNumber;
    private Long questionOrQuestionClusterId;
    private TypeContentOfExerciseFormatTOEIC typeContentOfExerciseFormatTOEIC;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getQuestionOrQuestionClusterId() {
        return questionOrQuestionClusterId;
    }

    public void setQuestionOrQuestionClusterId(Long questionOrQuestionClusterId) {
        this.questionOrQuestionClusterId = questionOrQuestionClusterId;
    }

    public TypeContentOfExerciseFormatTOEIC getTypeContentOfExerciseFormatTOEIC() {
        return typeContentOfExerciseFormatTOEIC;
    }

    public void setTypeContentOfExerciseFormatTOEIC(TypeContentOfExerciseFormatTOEIC typeContentOfExerciseFormatTOEIC) {
        this.typeContentOfExerciseFormatTOEIC = typeContentOfExerciseFormatTOEIC;
    }
}
