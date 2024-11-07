package com.javaweb.toeic.entity.compositekey;

import jakarta.persistence.Embeddable;

@Embeddable
public class Exercise_QuestionFormatTOEICPK {
    private Long exerciseFormatTOEICId;
    private Long questionFormatTOEICId;

    public Long getExerciseFormatTOEICId() {
        return exerciseFormatTOEICId;
    }

    public void setExerciseFormatTOEICId(Long exerciseFormatTOEICId) {
        this.exerciseFormatTOEICId = exerciseFormatTOEICId;
    }

    public Long getQuestionFormatTOEICId() {
        return questionFormatTOEICId;
    }

    public void setQuestionFormatTOEICId(Long questionFormatTOEICId) {
        this.questionFormatTOEICId = questionFormatTOEICId;
    }
}
