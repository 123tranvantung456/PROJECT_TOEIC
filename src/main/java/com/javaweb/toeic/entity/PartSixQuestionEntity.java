package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartSixQuestionType;
import com.javaweb.toeic.enums.TypeGrammar;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "part_six_question")
public class PartSixQuestionEntity extends AbstractQuestionOfPartThreeToSeven{
    @Enumerated(EnumType.STRING)
    @Column(name = "part_six_question_type")
    private PartSixQuestionType partSixQuestionType;
    @ElementCollection
    @CollectionTable(name = "part6_question_type_grammar", joinColumns = @JoinColumn(name = "part6_question_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "grammar_type")
    private Set<TypeGrammar> typeGrammars = new HashSet<>();
    @Column(name = "order_number_in_question_cluster")
    private Integer OrderNumberInQuestionCluster;

    @ManyToOne
    @JoinColumn(name = "question_cluster_id")
    private QuestionClusterOfPartSixEntity questionClusterOfPartSix;

}
