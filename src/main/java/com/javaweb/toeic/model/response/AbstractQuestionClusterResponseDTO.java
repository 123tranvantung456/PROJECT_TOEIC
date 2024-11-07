package com.javaweb.toeic.model.response;

import com.javaweb.toeic.enums.OrderNumberPartOfCluster;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractQuestionClusterResponseDTO {
    private Long id;
    private String mean;
    private OrderNumberPartOfCluster orderNumberPartOfCluster;

}
