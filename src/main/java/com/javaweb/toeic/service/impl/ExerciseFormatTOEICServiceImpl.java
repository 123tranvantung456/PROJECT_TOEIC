package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.enums.TypeContentOfExerciseFormatTOEIC;
import com.javaweb.toeic.model.response.OrderNumberOfExerciseFormatTOEICResponseDTO;
import com.javaweb.toeic.repository.Exercise_QuestionClusterFormatTOEICRepository;
import com.javaweb.toeic.repository.Exercise_QuestionFormatTOEICRepository;
import com.javaweb.toeic.service.ExerciseFormatTOEICService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ExerciseFormatTOEICServiceImpl implements ExerciseFormatTOEICService {

    @Autowired
    private Exercise_QuestionClusterFormatTOEICRepository exerciseQuestionClusterFormatTOEICRepository;

    @Autowired
    private Exercise_QuestionFormatTOEICRepository exerciseQuestionFormatTOEICRepository;

    @Override
    public List<OrderNumberOfExerciseFormatTOEICResponseDTO> getOrderNumberOfExerciseFormatToeicResponseDTOs(Long exerciseId) {
        List<Exercise_QuestionClusterFormatTOEICEntity> exerciseQuestionClusterFormatTOEICEntities =
                exerciseQuestionClusterFormatTOEICRepository.findByExerciseFormatTOEIC_Id(exerciseId);
        List<Exercise_QuestionFormatTOIECEntity> exerciseQuestionFormatTOIECEntities =
                exerciseQuestionFormatTOEICRepository.findByExerciseFormatTOEIC_Id(exerciseId);

        List<Object> questionFormatTOEICObjs = new ArrayList<>();
        questionFormatTOEICObjs.addAll(exerciseQuestionClusterFormatTOEICEntities);
        questionFormatTOEICObjs.addAll(exerciseQuestionFormatTOIECEntities);
        sortContentOfQuestionFormatTOEIC(questionFormatTOEICObjs);

        List<OrderNumberOfExerciseFormatTOEICResponseDTO> result = new ArrayList<>();
        for (Object questionFormatTOEICObj : questionFormatTOEICObjs) {
            OrderNumberOfExerciseFormatTOEICResponseDTO orderNumberOfExerciseFormatTOEICResponseDTO =
                    new OrderNumberOfExerciseFormatTOEICResponseDTO();

            if (questionFormatTOEICObj instanceof Exercise_QuestionClusterFormatTOEICEntity exercise_QuestionClusterFormatTOEICEntity) {
                orderNumberOfExerciseFormatTOEICResponseDTO.setOrderNumber(exercise_QuestionClusterFormatTOEICEntity.getOrderNumber());
                orderNumberOfExerciseFormatTOEICResponseDTO.setQuestionOrQuestionClusterId(exercise_QuestionClusterFormatTOEICEntity.getQuestionClusterEntity().getId());
                orderNumberOfExerciseFormatTOEICResponseDTO.setTypeContentOfExerciseFormatTOEIC(TypeContentOfExerciseFormatTOEIC.QUESTION_CLUSTER);
            } else if (questionFormatTOEICObj instanceof Exercise_QuestionFormatTOIECEntity exercise_QuestionFormatTOIECEntity) {
                orderNumberOfExerciseFormatTOEICResponseDTO.setOrderNumber(exercise_QuestionFormatTOIECEntity.getOrderNumber());
                orderNumberOfExerciseFormatTOEICResponseDTO.setQuestionOrQuestionClusterId(exercise_QuestionFormatTOIECEntity.getQuestionFormatTOEIC().getId());
                if(exercise_QuestionFormatTOIECEntity.getQuestionFormatTOEIC() instanceof PartOneQuestionEntity
                  || exercise_QuestionFormatTOIECEntity.getQuestionFormatTOEIC() instanceof PartTwoQuestionEntity || exercise_QuestionFormatTOIECEntity.getQuestionFormatTOEIC()
                        instanceof PartFiveQuestionEntity){
                    orderNumberOfExerciseFormatTOEICResponseDTO.setTypeContentOfExerciseFormatTOEIC(TypeContentOfExerciseFormatTOEIC.QUESTION_TYPE_ONE);
                }
                else {
                    orderNumberOfExerciseFormatTOEICResponseDTO.setTypeContentOfExerciseFormatTOEIC(TypeContentOfExerciseFormatTOEIC.QUESTION_TYPE_TWO);
                }
            }

            result.add(orderNumberOfExerciseFormatTOEICResponseDTO);
        }
        return result;
    }

    private void sortContentOfQuestionFormatTOEIC(List<Object> questionFormatTOEICObjs) {
        questionFormatTOEICObjs.sort(Comparator.comparingInt(o -> {
            if (o instanceof Exercise_QuestionClusterFormatTOEICEntity) {
                return ((Exercise_QuestionClusterFormatTOEICEntity) o).getOrderNumber();
            } else if (o instanceof Exercise_QuestionFormatTOIECEntity) {
                return ((Exercise_QuestionFormatTOIECEntity) o).getOrderNumber();
            }
            return Integer.MAX_VALUE;
        }));
    }
}

