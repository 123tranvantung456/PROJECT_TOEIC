package com.javaweb.toeic.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VocabularyListDTO {
    private Long id;
    private String name;
    private String description;
    private Integer orderNumber;
    private Long lessonId;
    private Long userId;

}
