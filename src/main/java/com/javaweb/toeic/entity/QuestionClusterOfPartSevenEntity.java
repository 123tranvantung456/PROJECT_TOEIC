package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartSevenQuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "question_cluster_of_part_seven")
public class QuestionClusterOfPartSevenEntity extends QuestionClusterEntity {

    // Getters and Setters
    @ElementCollection(targetClass = PartSevenQuestionType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "part_seven_question_type",
            joinColumns = @JoinColumn(name = "question_cluster_id"))
    @Column(name = "part_seven_question_type")
    private Set<PartSevenQuestionType> partSevenQuestionTypes;
    @OneToMany(mappedBy = "questionClusterOfPartSeven", fetch = FetchType.LAZY)
    private List<PartSevenQuestionEntity> partSevenQuestions;

    @Column(name = "image")
    private String image;

}
