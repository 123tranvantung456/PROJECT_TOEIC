package com.javaweb.toeic.test.theory;

import org.springframework.web.multipart.MultipartFile;

public class theoryDTO {
    private Long id;
    private Integer orderNumber;
    private MultipartFile theory;
    private MultipartFile video;
    private String name;
    private Long lessonId;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public MultipartFile getTheory() {
        return theory;
    }

    public void setTheory(MultipartFile theory) {
        this.theory = theory;
    }

    public MultipartFile getVideo() {
        return video;
    }

    public void setVideo(MultipartFile video) {
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
