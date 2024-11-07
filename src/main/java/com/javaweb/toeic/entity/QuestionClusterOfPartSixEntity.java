package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.TextFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "question_cluster_of_part_six")
public class QuestionClusterOfPartSixEntity extends  QuestionClusterEntity{
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "text_format")
    private TextFormat textFormat;
    @JoinColumn(name = "image")
    private String image;
    @OneToMany(mappedBy = "questionClusterOfPartSix", fetch = FetchType.LAZY)
    private List<PartSixQuestionEntity> partSixQuestions;
}