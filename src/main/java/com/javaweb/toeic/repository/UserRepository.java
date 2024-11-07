package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByIdAndExerciseFormatGrammars_Id(Long id, Long exerciseFormatGrammarId);
    Boolean existsByIdAndExerciseFormatTOIECs_Id(Long id, Long exerciseFormatTOIECId);
    Boolean existsByIdAndLessonContents_Id(Long id, Long lessonContentId);
    Boolean existsByUsernameOrEmail(String username, String email);
    Boolean existsByUsernameAndEmailAndStatus(String username, String email, Status status);
    Optional<UserEntity> findByEmail(String email);
}
