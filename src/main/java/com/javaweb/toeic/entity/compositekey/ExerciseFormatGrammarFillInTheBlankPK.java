package com.javaweb.toeic.entity.compositekey;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class ExerciseFormatGrammarFillInTheBlankPK implements Serializable {

    private Long exerciseFormatGrammarId;

    private Long fillInTheBlankQuestionId;

    // Default constructor
    public ExerciseFormatGrammarFillInTheBlankPK() {}

    // Parameterized constructor
    public ExerciseFormatGrammarFillInTheBlankPK(Long exerciseFormatGrammarId, Long fillInTheBlankQuestionId) {
        this.exerciseFormatGrammarId = exerciseFormatGrammarId;
        this.fillInTheBlankQuestionId = fillInTheBlankQuestionId;
    }

    // Getters and Setters
    public Long getExerciseFormatGrammarId() {
        return exerciseFormatGrammarId;
    }

    public void setExerciseFormatGrammarId(Long exerciseFormatGrammarId) {
        this.exerciseFormatGrammarId = exerciseFormatGrammarId;
    }

    public Long getFillInTheBlankQuestionId() {
        return fillInTheBlankQuestionId;
    }

    public void setFillInTheBlankQuestionId(Long fillInTheBlankQuestionId) {
        this.fillInTheBlankQuestionId = fillInTheBlankQuestionId;
    }

    // hashCode() and equals() methods
}
