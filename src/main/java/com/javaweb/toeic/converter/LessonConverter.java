package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.*;
import com.javaweb.toeic.enums.TypeContentOfLesson;
import com.javaweb.toeic.model.response.ContentNextResponseDTO;
import com.javaweb.toeic.model.response.ContentOfLessonResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LessonConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ContentOfLessonResponseDTO toContentOfLessonResponseDTO(Object contentOfLesson) {
        ContentOfLessonResponseDTO result = null;
        if (contentOfLesson instanceof ExerciseFormatTOEICEntity) {
            result = modelMapper.map(contentOfLesson,
                    ContentOfLessonResponseDTO.class);
            result.setTypeContent(TypeContentOfLesson.EXERCISE_FORMAT_TOEIC);
        }
        if (contentOfLesson instanceof ExerciseFormatGrammarEntity) {
            result = modelMapper.map( contentOfLesson,
                    ContentOfLessonResponseDTO.class);
            result.setTypeContent(TypeContentOfLesson.EXERCISE_FORMAT_GRAMMAR);
        } else if (contentOfLesson instanceof TheoryEntity) {
            result = modelMapper.map(contentOfLesson,
                    ContentOfLessonResponseDTO.class);
            result.setTypeContent(TypeContentOfLesson.THEORY);
        } else if (contentOfLesson instanceof VocabularyListInLessonEntity){
            result = modelMapper.map(contentOfLesson, ContentOfLessonResponseDTO.class);
            result.setTypeContent(TypeContentOfLesson.VOCABULARY_LIST);
        }
        return result;
    }

    public ContentNextResponseDTO toContentOfLessonNextResponseDTO(Object contentOfLesson) {
        ContentNextResponseDTO result = new ContentNextResponseDTO();
        if(contentOfLesson instanceof ExerciseFormatTOEICEntity exerciseFormatTOEICEntity) {
            result.setTypeContentOfLesson(TypeContentOfLesson.EXERCISE_FORMAT_TOEIC);
            result.setContentOfLessonNextId(exerciseFormatTOEICEntity.getId());
        }
        else if(contentOfLesson instanceof ExerciseFormatGrammarEntity exerciseFormatGrammarEntity) {
            result.setTypeContentOfLesson(TypeContentOfLesson.EXERCISE_FORMAT_GRAMMAR);
            result.setContentOfLessonNextId(exerciseFormatGrammarEntity.getId());
        }
        else if(contentOfLesson instanceof TheoryEntity theoryEntity) {
            result.setTypeContentOfLesson(TypeContentOfLesson.THEORY);
            result.setContentOfLessonNextId(theoryEntity.getId());
        }
        return result;
    }
}

