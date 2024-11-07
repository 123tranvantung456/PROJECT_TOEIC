package com.javaweb.toeic.api.web;

import com.javaweb.toeic.model.response.AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO;
import com.javaweb.toeic.service.QuestionClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/question-cluster")
public class QuestionClusterAPI {
    private final QuestionClusterService questionClusterService;
    @GetMapping("/{id}")
    public AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO
        getQuestionCluster (@PathVariable Long id) {
        return questionClusterService.getQuestionCluster(id);
    }

    @GetMapping("/question/{questionId}")
    public AbsQuestionClusterAndQuestionInQuestionClusterResponseDTO
    getQuestionInCluster (@PathVariable Long questionId) {
        return questionClusterService.getQuestionInCluster(questionId);
    }
}
