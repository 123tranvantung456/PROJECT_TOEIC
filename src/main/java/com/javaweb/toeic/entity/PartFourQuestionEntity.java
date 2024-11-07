package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartFourQuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "part_four_question")
public class PartFourQuestionEntity extends AbstractQuestionOfPartThreeToSeven{
    @Enumerated(EnumType.STRING)
    @Column(name = "part_four_question_type")
    private PartFourQuestionType partFourQuestionType;
    @Column(name = "order_number_in_question_cluster")
    private Integer OrderNumberInQuestionCluster;
    @Column(name = "question_content")
    private String questionContent;
    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "question_cluster_id")
    private QuestionClusterOfPartFourEntity questionClusterOfPartFour;

    public QuestionClusterOfPartFourEntity getQuestionClusterOfPartFourEntity() {
        return questionClusterOfPartFour;
    }

    public void setQuestionClusterOfPartFourEntity(QuestionClusterOfPartFourEntity questionClusterOfPartFourEntity) {
        this.questionClusterOfPartFour = questionClusterOfPartFourEntity;
    }

}
