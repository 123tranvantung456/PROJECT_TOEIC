package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "question_format_toeic")
@Inheritance(strategy = InheritanceType.JOINED)
public class QuestionFormatTOEICEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_explanation")
    private String answerExplanation;

    @Column(name = "correct_answer")
    private Character correctAnswer;

}
