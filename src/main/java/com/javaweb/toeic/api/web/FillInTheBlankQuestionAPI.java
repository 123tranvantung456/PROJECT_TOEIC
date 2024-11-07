package com.javaweb.toeic.api.web;

import com.javaweb.toeic.exception.DataNotFoundException;
import com.javaweb.toeic.model.response.FillInTheBlankQuestionResponseDTO;
import com.javaweb.toeic.model.response.custom.ResponseData;
import com.javaweb.toeic.service.FillInTheBlankQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/fill-in-the-blank-question")
public class FillInTheBlankQuestionAPI {
    private final FillInTheBlankQuestionService fillInTheBlankQuestionService;
    @GetMapping(value = "/{id}")
    public FillInTheBlankQuestionResponseDTO getFillInTheBlankQuestionById(@Valid @PathVariable Long id) {
        return fillInTheBlankQuestionService.getFillInTheBlankQuestionById(id);
    }

    @GetMapping(value = "/test/{id}")
    public ResponseData<FillInTheBlankQuestionResponseDTO> testFillInTheBlankQuestionById(@Valid @PathVariable Long id) {
        try {
            return new ResponseData<>(fillInTheBlankQuestionService.getFillInTheBlankQuestionById(id), HttpStatus.OK.value(), "fillInTheBlankQuestion");
        } catch (DataNotFoundException e){
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }
}
