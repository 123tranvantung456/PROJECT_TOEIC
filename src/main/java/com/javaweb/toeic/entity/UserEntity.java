package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.Status;
import com.javaweb.toeic.exception.PendingUserExistsException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "balance")
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<CourseEntity> courses =  new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RegistrationHistoryEntity> registrationHistory =  new ArrayList<>();
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<ExerciseFormatGrammarEntity> exerciseFormatGrammars = new ArrayList<>();
    @ManyToMany (mappedBy = "users", fetch = FetchType.LAZY)
    private List<TheoryEntity> lessonContents = new ArrayList<>();
    @ManyToMany (mappedBy = "users", fetch = FetchType.LAZY)
    private List<ExerciseFormatTOEICEntity> exerciseFormatTOIECs = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<VocabularyListNotInCourseEntity> vocabularyListNotInCourses = new ArrayList<>();
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<VocabularyEntity> vocabularies = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<VocabularyListInLessonEntity> vocabularyListInLessons = new ArrayList<>();
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private VerificationEntity verification;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+getRole().getName().toUpperCase()));
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != Status.SUSPENDING;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(status == Status.PENDING) throw new PendingUserExistsException("User is pending verification");
        return true;
    }

}
