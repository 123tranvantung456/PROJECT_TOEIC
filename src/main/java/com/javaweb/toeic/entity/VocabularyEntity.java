package com.javaweb.toeic.entity;

import com.javaweb.toeic.enums.PartsOfSpeech;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "vocabulary")
public class VocabularyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "english", nullable = false, length = 100)
    private String english;

    @Column(name = "vietnamese", nullable = false)
    private String vietnamese;

    @Column(name = "pronunciation", length = 100)
    private String pronunciation;

    @Column(name = "image", length = 255)
    private String image;

    @ElementCollection
    @CollectionTable(name = "vocabulary_example", joinColumns = @JoinColumn(name = "vocabulary_id"))
    @Column(name = "example", length = 1000)
    private List<String> examples;

    @Column(name = "definition", length = 2000)
    private String definition;

    @ElementCollection(targetClass = PartsOfSpeech.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "vocabulary_parts_of_speech", joinColumns = @JoinColumn(name = "vocabulary_id"))
    @Column(name = "parts_of_speech")
    private List<PartsOfSpeech> partsOfSpeech;
    @ManyToMany(mappedBy = "vocabularyEntities")
    private List<VocabularyListEntity> vocabularyList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "study_vocab_by_flash_card",joinColumns = @JoinColumn (name = "vocabulary_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<UserEntity> users = new ArrayList<>();
    // Getters and Setters

}
