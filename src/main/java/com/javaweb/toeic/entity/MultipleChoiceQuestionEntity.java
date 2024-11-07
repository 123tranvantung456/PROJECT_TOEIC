package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "multiple_choice_question")
public class MultipleChoiceQuestionEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "input")
    private String input;

    @Column(name = "vietnamese")
    private String vietnamese;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "incorrect_answer")
    private String incorrectAnswer;
    @OneToMany(mappedBy = "multipleChoiceQuestion", fetch = FetchType.LAZY)
    private List<ExerciseFormatGrammarMultipleChoiceEntity> exerciseFormatGrammarMultipleChoices =  new ArrayList<>();

}
