package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "theory")
public class TheoryEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "theory")
    private String theory;

    @Column(name = "video")
    private String video;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_lesson_content", joinColumns = @JoinColumn(name = "lesson_content_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<UserEntity> users  =  new ArrayList<>();

}

