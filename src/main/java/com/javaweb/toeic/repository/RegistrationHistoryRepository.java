package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.RegistrationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationHistoryRepository extends JpaRepository<RegistrationHistoryEntity, Long> {
    int countDistinctByCourseId(Long courseId);
}
