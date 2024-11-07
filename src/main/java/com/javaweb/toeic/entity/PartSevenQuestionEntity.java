package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartSevenQuestionType;
import com.javaweb.toeic.enums.StructureOfPartSeven;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "part_seven_question")
public class PartSevenQuestionEntity extends AbstractQuestionOfPartThreeToSeven{
    @Column(name = "question_content")
    private String questionContent;
    @Enumerated(EnumType.STRING)
    @Column(name = "part_seven_question_type")
    private PartSevenQuestionType partSevenQuestionType;
    @Enumerated(EnumType.STRING)
    @Column(name = "structure")
    private StructureOfPartSeven structureOfPartSeven;
    @Column(name = "order_number_in_question_cluster")
    private Integer OrderNumberInQuestionCluster;

    @ManyToOne
    @JoinColumn(name = "question_cluster_id")
    private QuestionClusterOfPartSevenEntity questionClusterOfPartSeven;

}
