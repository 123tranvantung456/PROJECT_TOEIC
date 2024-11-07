package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.TypeContentOfChapter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContentOfChapterResponseDTO {
    private Long id;
    private String name;
    protected TypeContentOfChapter typeContentOfChapter;

    public LessonInMyChapterResponseDTO convertToLessonInMyChapterResponseDTO() {
        if (typeContentOfChapter == TypeContentOfChapter.LESSON) {
            return (LessonInMyChapterResponseDTO)this;
        }
        return null;
    }

    public VocabularyListInChapterResponseDTO convertToVocabularyListInChapterResponseDTO() {
        if (typeContentOfChapter == TypeContentOfChapter.VOCABULARY_LIST){
            return (VocabularyListInChapterResponseDTO)this;
        }
        return null;
    }
}
