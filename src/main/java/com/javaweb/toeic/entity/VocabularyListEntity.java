package com.javaweb.toeic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "vocabulary_list")
@Inheritance(strategy = InheritanceType.JOINED)
public class VocabularyListEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "list_vocab",
            joinColumns = @JoinColumn(name = "list_vocab_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "vocab_id", nullable = false)
    )
    private List<VocabularyEntity> vocabularyEntities = new ArrayList<>();

}
