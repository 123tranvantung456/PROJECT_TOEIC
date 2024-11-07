package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO;

public interface QuestionClusterService {
    AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO getQuestionCluster(Long questionClusterId);
    AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO getQuestionInCluster(Long questionId);

}
