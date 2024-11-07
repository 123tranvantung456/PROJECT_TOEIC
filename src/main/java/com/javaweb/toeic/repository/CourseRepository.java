package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findByStatus(boolean status);
    List<CourseEntity> findByIdNot(Long id);
}

