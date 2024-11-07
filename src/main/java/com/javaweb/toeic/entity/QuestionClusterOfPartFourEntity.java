package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartFourQuestionClusterType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "question_cluster_of_part_four")
public class QuestionClusterOfPartFourEntity extends QuestionClusterEntity{
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "part_four_question_cluster_type")
    private PartFourQuestionClusterType partFourQuestionClusterType;
    @JoinColumn(name = "audio")
    private String audio;
    @OneToMany(mappedBy = "questionClusterOfPartFour", fetch = FetchType.LAZY)
    private List<PartFourQuestionEntity> partFourQuestions;
    @Column(name = "transcript")
    private String transcript;

}