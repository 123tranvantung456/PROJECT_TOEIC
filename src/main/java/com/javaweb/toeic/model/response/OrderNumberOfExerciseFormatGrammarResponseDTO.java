package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.TypeContentOfExerciseFormatGrammar;

public class OrderNumberOfExerciseFormatGrammarResponseDTO {
    private Integer orderNumber;
    private Long questionId;
    private TypeContentOfExerciseFormatGrammar typeContentOfExerciseFormatGrammar;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public TypeContentOfExerciseFormatGrammar getTypeContentOfExerciseFormatGrammar() {
        return typeContentOfExerciseFormatGrammar;
    }

    public void setTypeContentOfExerciseFormatGrammar(TypeContentOfExerciseFormatGrammar typeContentOfExerciseFormatGrammar) {
        this.typeContentOfExerciseFormatGrammar = typeContentOfExerciseFormatGrammar;
    }
}
