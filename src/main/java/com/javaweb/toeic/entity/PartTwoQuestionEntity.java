package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartTwoQuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "part_two_question")
public class PartTwoQuestionEntity extends  AbstractQuestionOfPartOneAndTwo{
    @Enumerated(EnumType.STRING)
    @Column(name = "part_two_question_type")
    private PartTwoQuestionType partTwoQuestionType;

}
