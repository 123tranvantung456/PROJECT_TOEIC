package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "role")
public class RoleEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private Role code;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserEntity> users  =  new ArrayList<>();
}
