package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.TypeContentOfChapter;

public class VocabularyListInChapterResponseDTO extends ContentOfChapterResponseDTO{
    private Boolean hasVocabulary;

    VocabularyListInChapterResponseDTO(){
        this.typeContentOfChapter = TypeContentOfChapter.VOCABULARY_LIST;
    }

    public Boolean getHasVocabulary() {
        return hasVocabulary;
    }

    public void setHasVocabulary(Boolean hasVocabulary) {
        this.hasVocabulary = hasVocabulary;
    }
}
