package com.javaweb.toeic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javaweb.toeic.enums.PartThreeQuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "part_three_question")
public class PartThreeQuestionEntity extends AbstractQuestionOfPartThreeToSeven{
    @Enumerated(EnumType.STRING)
    @Column(name = "part_three_question_type")
    private PartThreeQuestionType partThreeQuestionType;
    @Column(name = "order_number_in_question_cluster")
    private Integer OrderNumberInQuestionCluster;
    @Column(name = "question_content")
    private String questionContent;
    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "question_cluster_id")
    @JsonBackReference
    private QuestionClusterOfPartThreeEntity questionClusterOfPartThree;

}
