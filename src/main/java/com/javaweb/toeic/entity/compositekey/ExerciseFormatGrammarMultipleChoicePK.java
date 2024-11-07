package com.javaweb.toeic.entity.compositekey;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class ExerciseFormatGrammarMultipleChoicePK implements Serializable {

    private Long exerciseFormatGrammarId;

    private Long multipleChoiceQuestionId;

    // Default constructor
    public ExerciseFormatGrammarMultipleChoicePK() {}

    // Parameterized constructor
    public ExerciseFormatGrammarMultipleChoicePK(Long exerciseFormatGrammarId, Long multipleChoiceQuestionId) {
        this.exerciseFormatGrammarId = exerciseFormatGrammarId;
        this.multipleChoiceQuestionId = multipleChoiceQuestionId;
    }

    // Getters and Setters
    public Long getExerciseFormatGrammarId() {
        return exerciseFormatGrammarId;
    }

    public void setExerciseFormatGrammarId(Long exerciseFormatGrammarId) {
        this.exerciseFormatGrammarId = exerciseFormatGrammarId;
    }

    public Long getMultipleChoiceQuestionId() {
        return multipleChoiceQuestionId;
    }

    public void setMultipleChoiceQuestionId(Long multipleChoiceQuestionId) {
        this.multipleChoiceQuestionId = multipleChoiceQuestionId;
    }

    // hashCode() and equals() methods
}
