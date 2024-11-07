package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "fill_in_the_blank_question")
public class FillInTheBlankQuestionEntity  {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "input")
    private String input;

    @Column(name = "vietnamese")
    private String vietnamese;

    @Column(name = "english")
    private String english;
    @OneToMany (mappedBy = "fillInTheBlankQuestion", fetch = FetchType.LAZY)
    private List<ExerciseFormatGrammarFillInTheBlankEntity> exerciseFormatGrammarFillInTheBlanks =  new ArrayList<>();

}
