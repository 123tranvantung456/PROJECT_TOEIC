package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_format_grammar")
@Getter
@Setter
public class ExerciseFormatGrammarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column
    private String theory;

    @OneToMany(mappedBy = "exerciseFormatGrammar", cascade = CascadeType.ALL)
    private List<ExerciseFormatGrammarMultipleChoiceEntity> exerciseFormatGrammarMultipleChoiceEntities =  new ArrayList<>();

    @OneToMany(mappedBy = "exerciseFormatGrammar")
    private List<ExerciseFormatGrammarFillInTheBlankEntity> exerciseFormatGrammarFillInTheBlankEntities =  new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_exercise_format_grammar",
            joinColumns = @JoinColumn(name = "exercise_format_grammar_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<UserEntity> users = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;
}
