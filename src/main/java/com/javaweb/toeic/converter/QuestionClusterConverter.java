package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.enums.OrderNumberPartOfCluster;
import com.javaweb.toeic.model.response.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionClusterConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AbstractQuestionClusterResponseDTO toAbstractQuestionClusterResponseDTO(QuestionClusterEntity questionClusterEntity) {
        if (questionClusterEntity == null) {
            return null;
        }

        AbstractQuestionClusterResponseDTO responseDTO = null;
        if (questionClusterEntity instanceof QuestionClusterOfPartThreeEntity) {
            responseDTO = modelMapper.map(questionClusterEntity, QuestionClusterOfPartThreeResponseDTO.class);
            responseDTO.setOrderNumberPartOfCluster(OrderNumberPartOfCluster.PART_3);
        } else if (questionClusterEntity instanceof QuestionClusterOfPartFourEntity) {
            responseDTO = modelMapper.map(questionClusterEntity, QuestionClusterOfPartFourResponseDTO.class);
            responseDTO.setOrderNumberPartOfCluster(OrderNumberPartOfCluster.PART_4);
        } else if (questionClusterEntity instanceof QuestionClusterOfPartSixEntity) {
            responseDTO = modelMapper.map(questionClusterEntity, QuestionClusterOfPartSixResponseDTO.class);
            responseDTO.setOrderNumberPartOfCluster(OrderNumberPartOfCluster.PART_6);
        } else if (questionClusterEntity instanceof QuestionClusterOfPartSevenEntity) {
            responseDTO = modelMapper.map(questionClusterEntity, QuestionClusterOfPartSevenResponseDTO.class);
            responseDTO.setOrderNumberPartOfCluster(OrderNumberPartOfCluster.PART_7);
        }
        return responseDTO;
    }
}
