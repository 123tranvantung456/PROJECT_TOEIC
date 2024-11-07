package com.javaweb.toeic.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractQuestionOfPartOneAndTwoResponseDTO extends AbstractQuestionFormatTOEICResponseDTO {
    // Getter và Setter cho các thuộc tính chung
    private String audio;
    private String transcript;

}
