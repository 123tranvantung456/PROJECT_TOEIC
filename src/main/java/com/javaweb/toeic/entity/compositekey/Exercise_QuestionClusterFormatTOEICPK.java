package com.javaweb.toeic.entity.compositekey;

import jakarta.persistence.Embeddable;

@Embeddable
public class Exercise_QuestionClusterFormatTOEICPK {
    private Long exerciseFormatTOEICId;
    private Long questionClusterId;

    public Long getExerciseFormatTOEICId() {
        return exerciseFormatTOEICId;
    }

    public void setExerciseFormatTOEICId(Long exerciseFormatTOEICId) {
        this.exerciseFormatTOEICId = exerciseFormatTOEICId;
    }

    public Long getQuestionClusterId() {
        return questionClusterId;
    }

    public void setQuestionClusterId(Long questionClusterId) {
        this.questionClusterId = questionClusterId;
    }
}
