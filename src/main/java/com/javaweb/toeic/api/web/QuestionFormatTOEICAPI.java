package com.javaweb.toeic.api.web;

import com.javaweb.toeic.model.response.AbstractQuestionFormatTOEICResponseDTO;
import com.javaweb.toeic.service.QuestionFormatTOEICService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/question-format-toeic")
public class QuestionFormatTOEICAPI {
    @Autowired
    private QuestionFormatTOEICService questionFormatTOEICService;
    @GetMapping(value = "/type-one/{id}")
    public AbstractQuestionFormatTOEICResponseDTO getQuestionFormatTOEICAPI(@PathVariable Long id) {
        AbstractQuestionFormatTOEICResponseDTO abstractQuestionFormatTOEICResponseDTO = questionFormatTOEICService.getQuestionById(id);
        return abstractQuestionFormatTOEICResponseDTO;
    }
}