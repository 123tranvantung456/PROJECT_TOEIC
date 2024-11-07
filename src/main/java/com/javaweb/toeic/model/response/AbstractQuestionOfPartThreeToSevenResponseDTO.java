package com.javaweb.toeic.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractQuestionOfPartThreeToSevenResponseDTO extends AbstractQuestionFormatTOEICResponseDTO{
    private String contentAnswerA;
    private String contentAnswerB;
    private String contentAnswerC;
    private String contentAnswerD;

}