package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.PartFourQuestionClusterType;

import java.util.List;

public class QuestionClusterOfPartFourResponseDTO extends AbstractQuestionClusterResponseDTO {
    private PartFourQuestionClusterType partFourQuestionClusterType;
    private String audio;
    private List<QuestionPartFourResponseDTO> partFourQuestions;
    private String transcript;

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public List<QuestionPartFourResponseDTO> getPartFourQuestions() {
        return partFourQuestions;
    }

    public void setPartFourQuestions(List<QuestionPartFourResponseDTO> partFourQuestions) {
        this.partFourQuestions = partFourQuestions;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public PartFourQuestionClusterType getPartFourQuestionClusterType() {
        return partFourQuestionClusterType;
    }

    public void setPartFourQuestionClusterType(PartFourQuestionClusterType partFourQuestionClusterType) {
        this.partFourQuestionClusterType = partFourQuestionClusterType;
    }
}
