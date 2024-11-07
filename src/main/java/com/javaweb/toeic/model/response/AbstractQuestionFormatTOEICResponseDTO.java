package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.OrderNumberPart;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractQuestionFormatTOEICResponseDTO {
    private Long id;
    private String answerExplanation;
    private Character correctAnswer;
    private OrderNumberPart orderNumberPart;
}
