package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartOneQuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "part_one_question")
public class PartOneQuestionEntity extends AbstractQuestionOfPartOneAndTwo {
    @Enumerated(EnumType.STRING)
    @Column(name = "part_one_question_type")
    private PartOneQuestionType partOneQuestionType;

    @Column(name = "image")
    private String image;

}

