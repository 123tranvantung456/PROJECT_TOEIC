package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "lesson")
public class LessonEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "order_number")
    private Integer orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<TheoryEntity> lessonContents =  new ArrayList<>();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<ExerciseFormatTOEICEntity> exerciseFormatTOIECs =  new ArrayList<>();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<ExerciseFormatGrammarEntity> exerciseFormatGrammars =  new ArrayList<>();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<VocabularyListInLessonEntity> vocabularyListInLessons =  new ArrayList<>();

}
