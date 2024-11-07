package com.javaweb.toeic.entity;
import com.javaweb.toeic.enums.TypeGrammar;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "image")
    private String image ;
    @Column(name = "discount_percentage")
    private Integer discountPercentage ;
    @Column(name = "teacher_percentage")
    private Integer teacherPercentage ;
    @Column(name = "status")
    private Boolean status ;
    @Column(name = "duration_in_months")
    private Integer durationInMonths ;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private UserEntity teacher;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<ChapterEntity> chapters = new ArrayList<>();
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<RegistrationHistoryEntity> registrationHistory = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "target_of_course", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "target")
    private List<String> targetOfCourse = new ArrayList<>();

}
