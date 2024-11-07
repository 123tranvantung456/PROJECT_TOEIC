package com.javaweb.toeic.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javaweb.toeic.enums.ConversationTopic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "question_cluster_of_part_three")
public class QuestionClusterOfPartThreeEntity extends  QuestionClusterEntity{
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "conversation_topic")
    private ConversationTopic conversationTopic;
    @JoinColumn(name = "audio")
    private String audio;
    @OneToMany(mappedBy = "questionClusterOfPartThree", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PartThreeQuestionEntity> partThreeQuestions = new ArrayList<>();
    @Column(name = "transcript")
    private String transcript;

}
