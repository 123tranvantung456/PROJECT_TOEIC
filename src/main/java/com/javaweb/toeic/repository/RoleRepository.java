package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.RoleEntity;
import com.javaweb.toeic.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByCode(Role code);
}
