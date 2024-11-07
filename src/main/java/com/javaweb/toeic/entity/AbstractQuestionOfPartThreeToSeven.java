package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractQuestionOfPartThreeToSeven extends QuestionFormatTOEICEntity {

    @Column(name = "contentAnswerA", columnDefinition = "TEXT")
    private String contentAnswerA;

    @Column(name = "contentAnswerB", columnDefinition = "TEXT")
    private String contentAnswerB;

    @Column(name = "contentAnswerC", columnDefinition = "TEXT")
    private String contentAnswerC;

    @Column(name = "contentAnswerD", columnDefinition = "TEXT")
    private String contentAnswerD;

}
