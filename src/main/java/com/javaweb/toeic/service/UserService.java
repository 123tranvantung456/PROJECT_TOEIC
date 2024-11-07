package com.javaweb.toeic.service;

import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.enums.Status;
import com.javaweb.toeic.enums.TypeContentOfLesson;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService getUserDetailsService();
    int countCompleteContentOfLessonByUserInChapter(Long chapterId);
    boolean isCompleteContentOfLessonByUser(Long contentOfLessonId, TypeContentOfLesson typeContentOfLesson);
    void setStatus(UserEntity userEntity, Status status);
    String getEmail(String username);
}

