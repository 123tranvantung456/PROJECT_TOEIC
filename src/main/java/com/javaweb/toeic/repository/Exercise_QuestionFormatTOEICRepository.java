package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.Exercise_QuestionFormatTOIECEntity;
import com.javaweb.toeic.entity.compositekey.Exercise_QuestionFormatTOEICPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Exercise_QuestionFormatTOEICRepository extends JpaRepository<Exercise_QuestionFormatTOIECEntity, Exercise_QuestionFormatTOEICPK> {
    List<Exercise_QuestionFormatTOIECEntity> findByExerciseFormatTOEIC_Id(Long exId);
}
