package com.javaweb.toeic.entity;

import com.javaweb.toeic.entity.compositekey.Exercise_QuestionClusterFormatTOEICPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "exercise_question_cluster_formattoeic")
public class Exercise_QuestionClusterFormatTOEICEntity {
    @EmbeddedId
    private Exercise_QuestionClusterFormatTOEICPK id;

    @ManyToOne
    @MapsId("exerciseFormatTOEICId")
    @JoinColumn(name = "exercise_format_toeic_id")
    private ExerciseFormatTOEICEntity exerciseFormatTOEIC;

    @ManyToOne
    @MapsId("questionClusterId")
    @JoinColumn(name = "question_format_toeic_id")
    private QuestionClusterEntity questionClusterEntity;

    @Column(name = "order_number")
    private Integer orderNumber;

}
