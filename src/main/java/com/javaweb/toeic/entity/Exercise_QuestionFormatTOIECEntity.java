package com.javaweb.toeic.entity;

import com.javaweb.toeic.entity.compositekey.Exercise_QuestionFormatTOEICPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "exercise_question_format_toeic")
public class Exercise_QuestionFormatTOIECEntity {
    @EmbeddedId
    private Exercise_QuestionFormatTOEICPK id;

    @ManyToOne
    @MapsId("exerciseFormatTOEICId")
    @JoinColumn(name = "exercise_format_toeic_id")
    private ExerciseFormatTOEICEntity exerciseFormatTOEIC;

    @ManyToOne
    @MapsId("questionFormatTOEICId")
    @JoinColumn(name = "question_format_toeic_id")
    private QuestionFormatTOEICEntity questionFormatTOEIC;

    @Column(name = "order_number")
    private Integer orderNumber;
    //

}
