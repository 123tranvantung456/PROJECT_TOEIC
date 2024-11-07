package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.enums.OrderNumberPart;
import com.javaweb.toeic.model.response.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionFormatTOEICConverter {

    @Autowired
    private ModelMapper modelMapper;

    public AbstractQuestionFormatTOEICResponseDTO toQuestionFormatTOEICResponseDTO(QuestionFormatTOEICEntity questionFormatToeicEntity) {
        if (questionFormatToeicEntity == null) {
            return null;
        }

        AbstractQuestionFormatTOEICResponseDTO responseDTO = null;
        if (questionFormatToeicEntity instanceof PartOneQuestionEntity) {
            responseDTO = modelMapper.map(questionFormatToeicEntity, QuestionPartOneResponseDTO.class);
            responseDTO.setOrderNumberPart(OrderNumberPart.PART_1);
        } else if (questionFormatToeicEntity instanceof PartTwoQuestionEntity) {
            responseDTO = modelMapper.map(questionFormatToeicEntity, QuestionPartTwoResponseDTO.class);
            responseDTO.setOrderNumberPart(OrderNumberPart.PART_2);
        } else if (questionFormatToeicEntity instanceof PartThreeQuestionEntity) {
            responseDTO = modelMapper.map(questionFormatToeicEntity, QuestionPartThreeResponseDTO.class);
            responseDTO.setOrderNumberPart(OrderNumberPart.PART_3);
            ((QuestionPartThreeResponseDTO)responseDTO).setContentAnswerA(((PartThreeQuestionEntity) questionFormatToeicEntity).getContentAnswerA());
            ((QuestionPartThreeResponseDTO)responseDTO).setContentAnswerC(((PartThreeQuestionEntity) questionFormatToeicEntity).getContentAnswerC());
        } else if (questionFormatToeicEntity instanceof PartFourQuestionEntity) {
            responseDTO = modelMapper.map(questionFormatToeicEntity, QuestionPartFourResponseDTO.class);
            responseDTO.setOrderNumberPart(OrderNumberPart.PART_4);
        } else if (questionFormatToeicEntity instanceof PartFiveQuestionEntity) {
            responseDTO = modelMapper.map(questionFormatToeicEntity, QuestionPartFiveResponseDTO.class);
            responseDTO.setOrderNumberPart(OrderNumberPart.PART_5);
            ((QuestionPartFiveResponseDTO)responseDTO).setContentAnswerA(((PartFiveQuestionEntity) questionFormatToeicEntity).getContentAnswerA());
            ((QuestionPartFiveResponseDTO)responseDTO).setContentAnswerC(((PartFiveQuestionEntity) questionFormatToeicEntity).getContentAnswerC());
        } else if (questionFormatToeicEntity instanceof PartSixQuestionEntity) {
            responseDTO = modelMapper.map(questionFormatToeicEntity, QuestionPartSixResponseDTO.class);
            responseDTO.setOrderNumberPart(OrderNumberPart.PART_6);
            ((QuestionPartSixResponseDTO)responseDTO).setContentAnswerA(((PartSixQuestionEntity) questionFormatToeicEntity).getContentAnswerA());
            ((QuestionPartSixResponseDTO)responseDTO).setContentAnswerC(((PartSixQuestionEntity) questionFormatToeicEntity).getContentAnswerC());
        } else if (questionFormatToeicEntity instanceof PartSevenQuestionEntity) {
            responseDTO = modelMapper.map(questionFormatToeicEntity, QuestionPartSevenResponseDTO.class);
            responseDTO.setOrderNumberPart(OrderNumberPart.PART_7);
            ((QuestionPartSevenResponseDTO)responseDTO).setContentAnswerA(((PartSevenQuestionEntity) questionFormatToeicEntity).getContentAnswerA());
            ((QuestionPartSevenResponseDTO)responseDTO).setContentAnswerC(((PartSevenQuestionEntity) questionFormatToeicEntity).getContentAnswerC());
        }

        return responseDTO;
    }
}
