package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.LessonConverter;
import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.model.response.ContentNextResponseDTO;
import com.javaweb.toeic.model.response.ContentOfLessonResponseDTO;
import com.javaweb.toeic.repository.*;
import com.javaweb.toeic.service.LessonService;
import com.javaweb.toeic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private ExerciseFormatGrammarRepository exerciseFormatGrammarRepository;
    @Autowired
    private ExerciseFormatTOEICRepository exerciseFormatTOEICRepository;
    @Autowired
    private TheoryRepository theoryRepository;
    @Autowired
    private LessonConverter lessonConverter;
    @Autowired
    private UserService userService;
    @Autowired
    private VocabularyListInLessonRepository vocabularyListInLessonRepository;

    @Override
    public List<ContentOfLessonResponseDTO> getContentOfLesson(Long lessonId) {
        List<Object> contentOfLessons = new ArrayList<>();
        contentOfLessons.addAll(exerciseFormatGrammarRepository.findByLesson_Id(lessonId));
        contentOfLessons.addAll(exerciseFormatTOEICRepository.findByLesson_Id(lessonId));
        contentOfLessons.addAll(theoryRepository.findByLesson_Id(lessonId));
        contentOfLessons.addAll(vocabularyListInLessonRepository.findByLesson_IdAndUser_Id(lessonId, 1L));
        sortContentOfLessons(contentOfLessons);
        List<ContentOfLessonResponseDTO> result = new ArrayList<>();
        for (Object content : contentOfLessons) {
            ContentOfLessonResponseDTO contentOfLessonResponseDTO =
                    lessonConverter.toContentOfLessonResponseDTO(content);
            contentOfLessonResponseDTO.setIsComplete(userService.isCompleteContentOfLessonByUser(
                    contentOfLessonResponseDTO.getId(), contentOfLessonResponseDTO.getTypeContent()));
            result.add(contentOfLessonResponseDTO);
        }
        return result;
    }

    @Override
    public ContentNextResponseDTO getFirstContentOfLesson(Long lessonId) {
        TheoryEntity theoryEntity =
                theoryRepository.findFirstByLesson_IdOrderByOrderNumberAsc
                        (lessonId).orElseGet(() -> {
                    TheoryEntity defaultTheory = new TheoryEntity();
                    defaultTheory.setOrderNumber(0);
                    return defaultTheory;
                });
        ExerciseFormatGrammarEntity exerciseFormatGrammarEntity =
                exerciseFormatGrammarRepository.findFirstByLesson_IdOrderByOrderNumberAsc
                        (lessonId).orElseGet(() -> {
                    ExerciseFormatGrammarEntity defaultExerciseFormatGrammar = new ExerciseFormatGrammarEntity();
                    defaultExerciseFormatGrammar.setOrderNumber(0);
                    return defaultExerciseFormatGrammar;
                });
        ExerciseFormatTOEICEntity exerciseFormatTOEICEntity =
                exerciseFormatTOEICRepository.findFirstByLesson_IdOrderByOrderNumberAsc
                        (lessonId).orElseGet(() -> {
                    ExerciseFormatTOEICEntity exerciseFormatTOEIC = new ExerciseFormatTOEICEntity();
                    exerciseFormatTOEIC.setOrderNumber(0);
                    return exerciseFormatTOEIC;
                });
        Object firstContentOfLesson = null;
        int orderNumberMin = Integer.MAX_VALUE;
        if(theoryEntity.getOrderNumber() < orderNumberMin && theoryEntity.getOrderNumber() > 0) {
            firstContentOfLesson = theoryEntity;
            orderNumberMin = theoryEntity.getOrderNumber();
        }
        if(exerciseFormatGrammarEntity.getOrderNumber() < orderNumberMin && exerciseFormatGrammarEntity.getOrderNumber() > 0) {
            firstContentOfLesson = exerciseFormatGrammarEntity;
            orderNumberMin = exerciseFormatGrammarEntity.getOrderNumber();
        }
        if(exerciseFormatTOEICEntity.getOrderNumber() < orderNumberMin && exerciseFormatTOEICEntity.getOrderNumber() > 0) {
            firstContentOfLesson = exerciseFormatTOEICEntity;
        }
        if(firstContentOfLesson == null) {
            return new ContentNextResponseDTO();
        }
        return lessonConverter.toContentOfLessonNextResponseDTO(firstContentOfLesson);
    }


    private void sortContentOfLessons(List<Object> contentOfLessons) {
        contentOfLessons.sort(Comparator.comparingInt(o -> {
            if (o instanceof ExerciseFormatGrammarEntity) {
                return ((ExerciseFormatGrammarEntity) o).getOrderNumber();
            } else if (o instanceof ExerciseFormatTOEICEntity) {
                return ((ExerciseFormatTOEICEntity) o).getOrderNumber();
            } else if (o instanceof TheoryEntity) {
                return ((TheoryEntity) o).getOrderNumber();
            } else if (o instanceof VocabularyListInLessonEntity) {
                return ((VocabularyListInLessonEntity) o).getOrderNumber();
            }
            return Integer.MAX_VALUE;
        }));
    }
}
