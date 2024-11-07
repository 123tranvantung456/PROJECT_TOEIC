package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Long> {
    boolean existsByVocabularyList_Id(Long id);
    int countByVocabularyList_Id(Long listId);
    int countByUsers_IdAndVocabularyList_Id(Long userId, Long vocabularyListId);
    List<VocabularyEntity> findByVocabularyList_IdOrderByEnglish(Long listId);
    boolean existsByIdAndUsers_Id(Long id, Long userId);
    VocabularyEntity findFirstByEnglishAndVocabularyList_IdOrderByEnglish(String name, Long listId); // upload

    @Query(value = "SELECT lv.vocab_id FROM toeic_db.list_vocab lv WHERE lv.list_vocab_id = :listId AND lv.vocab_id NOT IN :ids ORDER BY lv.vocab_id ASC LIMIT 7", nativeQuery = true)
    List<Long> findTop7ByIdNotInAndVocabularyList_IdOrderByIdAsc(List<Long> ids, Long listId);

    @Query(value = "SELECT s.vocabulary_id  FROM  toeic_db.study_vocab_by_flash_card s INNER JOIN list_vocab lv ON s.vocabulary_id = lv.vocab_id WHERE s.user_id = :userId and lv.list_vocab_id = :listId", nativeQuery = true)
    List<Long> findByUsers_Id(Long userId, Long listId);

    @Query(value = "SELECT lv.vocab_id FROM toeic_db.list_vocab lv WHERE lv.list_vocab_id = :listId AND lv.vocab_id NOT IN :ids ORDER BY lv.vocab_id ASC LIMIT 1", nativeQuery = true)
    Long findTop1ByIdNotInAndVocabularyList_IdOrderByIdAsc(List<Long> ids, Long listId);
}