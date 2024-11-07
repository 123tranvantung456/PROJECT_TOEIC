package com.javaweb.toeic.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO {
    private AbstractQuestionClusterResponseDTO clusterResponseDTO;
    private List<AbstractQuestionFormatTOEICResponseDTO> formatTOEICResponseDTOs;
}
