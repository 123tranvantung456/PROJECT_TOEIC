package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.ExerciseFormatGrammarConverter;
import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.enums.TypeContentOfExerciseFormatGrammar;
import com.javaweb.toeic.model.response.ExerciseFormatGrammarResponseDTO;
import com.javaweb.toeic.model.response.OrderNumberOfExerciseFormatGrammarResponseDTO;
import com.javaweb.toeic.repository.ExerciseFormatGrammarFillInTheBlankRepository;
import com.javaweb.toeic.repository.ExerciseFormatGrammarMultipleChoiceRepository;
import com.javaweb.toeic.repository.ExerciseFormatGrammarRepository;
import com.javaweb.toeic.service.ExerciseFormatGrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
public class ExerciseFormatGrammarServiceImpl implements ExerciseFormatGrammarService {
    @Autowired
    private ExerciseFormatGrammarFillInTheBlankRepository exerciseFormatGrammarFillInTheBlankRepository;
    @Autowired
    private ExerciseFormatGrammarMultipleChoiceRepository exerciseFormatGrammarMultipleChoiceRepository;
    @Autowired
    private ExerciseFormatGrammarRepository exerciseFormatGrammarRepository;
    @Autowired
    private ExerciseFormatGrammarConverter exerciseFormatGrammarConverter;

    @Override
    public List<OrderNumberOfExerciseFormatGrammarResponseDTO> getOrderNumberInExerciseFormatGrammar
            (Long exerciseId) {
        List<ExerciseFormatGrammarFillInTheBlankEntity> exerciseFormatGrammarFillInTheBlankRepositories =
                exerciseFormatGrammarFillInTheBlankRepository.findByExerciseFormatGrammar_Id(exerciseId);
        List<ExerciseFormatGrammarMultipleChoiceEntity> exerciseFormatGrammarMultipleChoiceEntities =
                exerciseFormatGrammarMultipleChoiceRepository.findByExerciseFormatGrammar_Id(exerciseId);
        List<Object> questionFormatGrammarObjs = new ArrayList<>();
        questionFormatGrammarObjs.addAll(exerciseFormatGrammarFillInTheBlankRepositories);
        questionFormatGrammarObjs.addAll(exerciseFormatGrammarMultipleChoiceEntities);
        sortContentOfQuestionFormatGrammar(questionFormatGrammarObjs);
        List<OrderNumberOfExerciseFormatGrammarResponseDTO> result = new ArrayList<>();
        for (Object questionFormatGrammarObj : questionFormatGrammarObjs) {
            OrderNumberOfExerciseFormatGrammarResponseDTO orderNumberOfExerciseFormatGrammarResponseDTO =
                    new OrderNumberOfExerciseFormatGrammarResponseDTO();
            if(questionFormatGrammarObj instanceof ExerciseFormatGrammarFillInTheBlankEntity exerciseFormatGrammarFillInTheBlankEntity){
                orderNumberOfExerciseFormatGrammarResponseDTO.setOrderNumber(exerciseFormatGrammarFillInTheBlankEntity.getOrderNumber());
                orderNumberOfExerciseFormatGrammarResponseDTO.setQuestionId(exerciseFormatGrammarFillInTheBlankEntity.getFillInTheBlankQuestion().getId());
                orderNumberOfExerciseFormatGrammarResponseDTO.setTypeContentOfExerciseFormatGrammar(TypeContentOfExerciseFormatGrammar.FILL_IN_THE_BLANK_QUESTION);
            }
            else if(questionFormatGrammarObj instanceof ExerciseFormatGrammarMultipleChoiceEntity exerciseFormatGrammarMultipleChoiceEntity) {
                orderNumberOfExerciseFormatGrammarResponseDTO.setOrderNumber(exerciseFormatGrammarMultipleChoiceEntity.getOrderNumber());
                orderNumberOfExerciseFormatGrammarResponseDTO.setQuestionId(exerciseFormatGrammarMultipleChoiceEntity.getMultipleChoiceQuestion().getId());
                orderNumberOfExerciseFormatGrammarResponseDTO.setTypeContentOfExerciseFormatGrammar(TypeContentOfExerciseFormatGrammar.MULTIPLE_CHOICE_QUESTION);
            }
            result.add(orderNumberOfExerciseFormatGrammarResponseDTO);
        }
        return result;
    }

    @Override
    public ExerciseFormatGrammarResponseDTO getExerciseFormatGrammarCurrent(Long exerciseId) {
        ExerciseFormatGrammarEntity exerciseFormatGrammarEntity = exerciseFormatGrammarRepository.findById(exerciseId).orElse(null);
        return exerciseFormatGrammarConverter.toExerciseFormatGrammarResponseDTO(exerciseFormatGrammarEntity);
    }

    private void sortContentOfQuestionFormatGrammar(List<Object> sortContentOfQuestionFormatGrammar) {
        sortContentOfQuestionFormatGrammar.sort(Comparator.comparingInt(o -> {
            if (o instanceof ExerciseFormatGrammarFillInTheBlankEntity) {
                return ((ExerciseFormatGrammarFillInTheBlankEntity) o).getOrderNumber();
            } else if (o instanceof ExerciseFormatGrammarMultipleChoiceEntity) {
                return ((ExerciseFormatGrammarMultipleChoiceEntity) o).getOrderNumber();
            }
            return Integer.MAX_VALUE;
        }));
    }
}
