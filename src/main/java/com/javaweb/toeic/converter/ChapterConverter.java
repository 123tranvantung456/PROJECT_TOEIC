package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.ChapterEntity;
import com.javaweb.toeic.entity.LessonEntity;
import com.javaweb.toeic.entity.VocabularyListInCourseEntity;
import com.javaweb.toeic.model.response.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChapterConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ContentOfChapterResponseDTO toContentOfChapterResponseDTO(Object contentOfChapter){
        if (contentOfChapter instanceof LessonEntity) {
            return modelMapper.map(contentOfChapter, LessonInMyChapterResponseDTO.class);
        } else if (contentOfChapter instanceof VocabularyListInCourseEntity) {
            return modelMapper.map(contentOfChapter, VocabularyListInChapterResponseDTO.class);
        } else {
            throw new IllegalArgumentException("Unsupported content type: " + contentOfChapter.getClass());
        }
    }

    public ChapterInMyCourseResponseDTO tochapterInMyCourseResponseDTO(ChapterEntity chapterEntity){
        return  modelMapper.map(chapterEntity, ChapterInMyCourseResponseDTO.class);
    }

    public ChapterResponseDTO toChapterResponseDTO(ChapterEntity chapterEntity){
        return modelMapper.map(chapterEntity, ChapterResponseDTO.class);
    }
}
