package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractQuestionOfPartOneAndTwo extends QuestionFormatTOEICEntity {
    // Getter và Setter cho các thuộc tính chung
    @Column(name = "audio")
    private String audio;

    @Column(name = "transcript")
    private String transcript;

}
