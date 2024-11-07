package com.javaweb.toeic.entity;

import com.javaweb.toeic.entity.compositekey.ExerciseFormatGrammarFillInTheBlankPK;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "exercise_format_grammar_fill_in_the_blank")
public class ExerciseFormatGrammarFillInTheBlankEntity {

    @EmbeddedId
    private ExerciseFormatGrammarFillInTheBlankPK id;

    @ManyToOne
    @MapsId("exerciseFormatGrammarId")
    @JoinColumn(name = "exercise_format_grammar_id")
    private ExerciseFormatGrammarEntity exerciseFormatGrammar;

    @ManyToOne
    @MapsId("fillInTheBlankQuestionId")
    @JoinColumn(name = "fill_in_the_blank_question_id")
    private FillInTheBlankQuestionEntity fillInTheBlankQuestion;

    @Column(name = "order_number")
    private Integer orderNumber;

}
