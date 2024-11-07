package com.javaweb.toeic.entity;

import com.javaweb.toeic.entity.compositekey.ExerciseFormatGrammarMultipleChoicePK;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "exercise_format_grammar_multiple_choice")
public class ExerciseFormatGrammarMultipleChoiceEntity {

    @EmbeddedId
    private ExerciseFormatGrammarMultipleChoicePK id;

    @ManyToOne
    @MapsId("exerciseFormatGrammarId")
    @JoinColumn(name = "exercise_format_grammar_id")
    private ExerciseFormatGrammarEntity exerciseFormatGrammar;

    @ManyToOne
    @MapsId("multipleChoiceQuestionId")
    @JoinColumn(name = "multiple_choice_question_id")
    private MultipleChoiceQuestionEntity multipleChoiceQuestion;

    @Column(name = "order_number")
    private Integer orderNumber;

}
