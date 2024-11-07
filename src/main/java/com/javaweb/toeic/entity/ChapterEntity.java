package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "chapter")
public class ChapterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "order_number")
    private Integer orderNumber;

    @OneToMany(mappedBy = "chapter", fetch = FetchType.LAZY)
    private List<LessonEntity> lessons = new ArrayList<LessonEntity>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(mappedBy = "chapter", fetch = FetchType.LAZY)
    private List<VocabularyListInCourseEntity> vocabularyListInCourses = new ArrayList<>();

}
