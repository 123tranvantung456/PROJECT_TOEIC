package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.Exercise_QuestionClusterFormatTOEICEntity;
import com.javaweb.toeic.entity.compositekey.Exercise_QuestionClusterFormatTOEICPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Exercise_QuestionClusterFormatTOEICRepository extends JpaRepository<Exercise_QuestionClusterFormatTOEICEntity, Exercise_QuestionClusterFormatTOEICPK> {
    List<Exercise_QuestionClusterFormatTOEICEntity> findByExerciseFormatTOEIC_Id(Long exId);
}
