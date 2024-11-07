package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.entity.ExerciseFormatGrammarEntity;
import com.javaweb.toeic.entity.ExerciseFormatTOEICEntity;
import com.javaweb.toeic.entity.TheoryEntity;
import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.enums.Status;
import com.javaweb.toeic.enums.TypeContentOfLesson;
import com.javaweb.toeic.repository.UserRepository;
import com.javaweb.toeic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private int countCompleteExerciseFormatGrammarsByUserInChapter(Long chapterId) {
        int result = 0;
        Long id = 1L;
        UserEntity userEntity = userRepository.findById(id).get();
        List<ExerciseFormatGrammarEntity> exerciseFormatGrammarEntities = userEntity.getExerciseFormatGrammars();
        for (ExerciseFormatGrammarEntity exerciseFormatGrammarEntity : exerciseFormatGrammarEntities) {
            if(exerciseFormatGrammarEntity.getLesson().getChapter().getId().equals(chapterId)){
                result++;
            }
        }
        return result;
    }
    private int countCompleteLessonContentByUserInChapter(Long chapterId) {
        int result = 0;
        Long id = 1L;
        UserEntity userEntity = userRepository.findById(id).get();
        List<TheoryEntity> lessonContentEntities = userEntity.getLessonContents();
        for (TheoryEntity theoryEntity : lessonContentEntities) {
            if(theoryEntity.getLesson().getChapter().getId().equals(chapterId)){
                result++;
            }
        }
        return result;
    }
    private int countCompleteExerciseFormatTOIECByUserInChapter(Long chapterId) {
        int result = 0;
        Long id = 1L;
        UserEntity userEntity = userRepository.findById(id).get();
        List<ExerciseFormatTOEICEntity> exerciseFormatTOIECEntities = userEntity.getExerciseFormatTOIECs();
        for (ExerciseFormatTOEICEntity exerciseFormatTOEICEntity : exerciseFormatTOIECEntities) {
            if(exerciseFormatTOEICEntity.getLesson().getChapter().getId().equals(chapterId)){
                result++;
            }
        }
        return result;
    }

//  dùng lambda thay cho anonymous inner class (doi voi interface chi co 1 phuong thuc)
    @Override
    public UserDetailsService getUserDetailsService() {
        System.out.println("ok");
        return username -> userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
    }

//    // truyền thống : dùng anonymous inner class
//    @Override
//    public UserDetailsService getUserDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return userRepository.findByUsername(username);
//            }
//        };
//    }

    @Override
    public int countCompleteContentOfLessonByUserInChapter(Long chapterId) {
         int result = countCompleteExerciseFormatTOIECByUserInChapter(chapterId)
                + countCompleteExerciseFormatGrammarsByUserInChapter(chapterId)
                + countCompleteLessonContentByUserInChapter(chapterId);
        return result;
    }

    @Override
    public boolean isCompleteContentOfLessonByUser(Long contentOfLessonId, TypeContentOfLesson typeContentOfLesson) {
        if(typeContentOfLesson == TypeContentOfLesson.THEORY){
            return userRepository.existsByIdAndLessonContents_Id(1L, contentOfLessonId);
        } else if (typeContentOfLesson == TypeContentOfLesson.EXERCISE_FORMAT_GRAMMAR) {
            return userRepository.existsByIdAndExerciseFormatGrammars_Id(1L, contentOfLessonId);
        } else if (typeContentOfLesson == TypeContentOfLesson.EXERCISE_FORMAT_TOEIC) {
            return userRepository.existsByIdAndExerciseFormatTOIECs_Id(1L, contentOfLessonId);
        }
        return false;
    }

    @Override
    public void setStatus(UserEntity userEntity, Status status) {
        userEntity.setStatus(status);
        userRepository.save(userEntity);
    }

    @Override
    public String getEmail(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
        return user.getEmail();
    }
}
