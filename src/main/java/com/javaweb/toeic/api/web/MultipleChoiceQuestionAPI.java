package com.javaweb.toeic.api.web;

import com.javaweb.toeic.model.response.MultipleChoiceQuestionResponseDTO;
import com.javaweb.toeic.service.MultipleChoiceQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/multiple-choice-question")
public class MultipleChoiceQuestionAPI {
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    @GetMapping("/{id}")
    public MultipleChoiceQuestionResponseDTO getMultipleChoiceQuestionById(@PathVariable Long id) {
        return multipleChoiceQuestionService.getMultipleChoiceQuestionById(id);
    }
}
