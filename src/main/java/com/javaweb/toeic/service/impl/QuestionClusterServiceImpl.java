package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.QuestionClusterConverter;
import com.javaweb.toeic.converter.QuestionFormatTOEICConverter;
import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.model.response.*;
import com.javaweb.toeic.repository.QuestionClusterRepository;
import com.javaweb.toeic.repository.QuestionFormatTOEICRepository;
import com.javaweb.toeic.service.QuestionClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionClusterServiceImpl implements QuestionClusterService {

    @Autowired
    private QuestionClusterRepository questionClusterRepository;

    @Autowired
    private QuestionClusterConverter questionClusterConverter;

    @Autowired
    private QuestionFormatTOEICConverter questionFormatTOEICConverter;

    @Autowired
    private QuestionFormatTOEICRepository questionFormatTOEICRepository;
    @Override
    public AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO getQuestionCluster( Long id) {
        QuestionClusterEntity questionClusterEntity = questionClusterRepository.findById(id).orElse(null);
        if (questionClusterEntity == null) {
            return null;
        }

        AbstractQuestionClusterResponseDTO clusterResponseDTO = convertToClusterResponseDTO(questionClusterEntity);
        List<AbstractQuestionFormatTOEICResponseDTO> formatTOEICResponseDTOs = convertToFormatResponseDTOs(questionClusterEntity);
        AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO responseDTO = new AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO();
        responseDTO.setClusterResponseDTO(clusterResponseDTO);
        responseDTO.setFormatTOEICResponseDTOs(formatTOEICResponseDTOs);
        return responseDTO;
    }

    @Override
    public AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO getQuestionInCluster(Long questionId) {
        QuestionFormatTOEICEntity questionFormatTOEICEntity = questionFormatTOEICRepository.findById(questionId).orElse(null);
        QuestionClusterEntity questionClusterEntity = null;
        if (questionFormatTOEICEntity instanceof PartThreeQuestionEntity partThreeQuestionEntity){
            questionClusterEntity = partThreeQuestionEntity.getQuestionClusterOfPartThree();
        }
        if (questionFormatTOEICEntity instanceof PartFourQuestionEntity partFourQuestionEntity){
            questionClusterEntity = partFourQuestionEntity.getQuestionClusterOfPartFourEntity();
        }
        if (questionFormatTOEICEntity instanceof PartSixQuestionEntity partSixQuestionEntity){
            questionClusterEntity = partSixQuestionEntity.getQuestionClusterOfPartSix();
        }
        if (questionFormatTOEICEntity instanceof PartSevenQuestionEntity partSevenQuestionEntity){
            questionClusterEntity = partSevenQuestionEntity.getQuestionClusterOfPartSeven();
        }
        AbstractQuestionClusterResponseDTO questionClusterResponseDTO = convertToClusterResponseDTO(questionClusterEntity);
        AbstractQuestionFormatTOEICResponseDTO abstractQuestionFormatTOEICResponseDTO = convertToAbstractQuestionFormatTOEICResponseDTO(questionFormatTOEICEntity);
        AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO responseDTO = new AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO();
        responseDTO.setClusterResponseDTO(questionClusterResponseDTO);
        List<AbstractQuestionFormatTOEICResponseDTO> formatTOEICResponseDTOs = new ArrayList<>();
        formatTOEICResponseDTOs.add(abstractQuestionFormatTOEICResponseDTO);
        responseDTO.setFormatTOEICResponseDTOs(formatTOEICResponseDTOs);
        return responseDTO;
    }

    private AbstractQuestionFormatTOEICResponseDTO convertToAbstractQuestionFormatTOEICResponseDTO(QuestionFormatTOEICEntity entity) {
        return questionFormatTOEICConverter.toQuestionFormatTOEICResponseDTO(entity);
    }

    private AbstractQuestionClusterResponseDTO convertToClusterResponseDTO(QuestionClusterEntity entity) {
        return questionClusterConverter.toAbstractQuestionClusterResponseDTO(entity);
    }

    private List<AbstractQuestionFormatTOEICResponseDTO> convertToFormatResponseDTOs(QuestionClusterEntity entity) {
        if (entity instanceof QuestionClusterOfPartThreeEntity) {
            return convertPartThreeQuestions((QuestionClusterOfPartThreeEntity) entity);
        } else if (entity instanceof QuestionClusterOfPartFourEntity) {
            return convertPartFourQuestions((QuestionClusterOfPartFourEntity) entity);
        } else if (entity instanceof QuestionClusterOfPartSixEntity) {
            return convertPartSixQuestions((QuestionClusterOfPartSixEntity) entity);
        } else if (entity instanceof QuestionClusterOfPartSevenEntity) {
            return convertPartSevenQuestions((QuestionClusterOfPartSevenEntity) entity);
        }
        return null;
    }

    private List<AbstractQuestionFormatTOEICResponseDTO> convertPartThreeQuestions(QuestionClusterOfPartThreeEntity entity) {
        List<AbstractQuestionFormatTOEICResponseDTO> responseDTOs = new ArrayList<>();
        for (PartThreeQuestionEntity question : entity.getPartThreeQuestions()) {
            responseDTOs.add(questionFormatTOEICConverter.toQuestionFormatTOEICResponseDTO(question));
        }
        return responseDTOs;
    }

    private List<AbstractQuestionFormatTOEICResponseDTO> convertPartFourQuestions(QuestionClusterOfPartFourEntity entity) {
        List<AbstractQuestionFormatTOEICResponseDTO> responseDTOs = new ArrayList<>();
        for (PartFourQuestionEntity question : entity.getPartFourQuestions()) {
            responseDTOs.add(questionFormatTOEICConverter.toQuestionFormatTOEICResponseDTO(question));
        }
        return responseDTOs;
    }

    private List<AbstractQuestionFormatTOEICResponseDTO> convertPartSixQuestions(QuestionClusterOfPartSixEntity entity) {
        List<AbstractQuestionFormatTOEICResponseDTO> responseDTOs = new ArrayList<>();
        for (PartSixQuestionEntity question : entity.getPartSixQuestions()) {
            responseDTOs.add(questionFormatTOEICConverter.toQuestionFormatTOEICResponseDTO(question));
        }
        return responseDTOs;
    }

    private List<AbstractQuestionFormatTOEICResponseDTO> convertPartSevenQuestions(QuestionClusterOfPartSevenEntity entity) {
        List<AbstractQuestionFormatTOEICResponseDTO> responseDTOs = new ArrayList<>();
        for (PartSevenQuestionEntity question : entity.getPartSevenQuestions()) {
            responseDTOs.add(questionFormatTOEICConverter.toQuestionFormatTOEICResponseDTO(question));
        }
        return responseDTOs;
    }
}
