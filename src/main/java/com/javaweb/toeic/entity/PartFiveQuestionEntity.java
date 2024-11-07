package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartFiveQuestionType;
import com.javaweb.toeic.enums.TypeGrammar;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "part_five_question")
public class PartFiveQuestionEntity extends AbstractQuestionOfPartThreeToSeven{
    @Column(name = "question_content")
    private String questionContent;
    @Enumerated(EnumType.STRING)
    @Column(name = "part_five_question_type")
    private PartFiveQuestionType partFiveQuestionType;

    @ElementCollection
    @CollectionTable(name = "part5_question_type_grammar", joinColumns = @JoinColumn(name = "part5_question_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "grammar_type")
    private Set<TypeGrammar> typeGrammars = new HashSet<>();

}
