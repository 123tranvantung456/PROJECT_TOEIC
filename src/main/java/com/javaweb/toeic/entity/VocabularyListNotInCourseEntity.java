package com.javaweb.toeic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "vocabulary_list_not_in_course")
//@Table(name = "vocabulary_list_not_in_course")
public class VocabularyListNotInCourseEntity extends VocabularyListEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
