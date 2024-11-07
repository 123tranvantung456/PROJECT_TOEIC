package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.ConversationTopic;

public class QuestionClusterOfPartThreeResponseDTO extends AbstractQuestionClusterResponseDTO{
    private ConversationTopic conversationTopic;
    private String audio;
    private String transcript;

    public ConversationTopic getConversationTopic() {
        return conversationTopic;
    }

    public void setConversationTopic(ConversationTopic conversationTopic) {
        this.conversationTopic = conversationTopic;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }
}
