package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "exercise_format_TOEIC")
public class ExerciseFormatTOEICEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "order_number")
    private Integer orderNumber;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_exercise_format_TOEIC",joinColumns = @JoinColumn (name = "exercise_format_TOEIC_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

}
