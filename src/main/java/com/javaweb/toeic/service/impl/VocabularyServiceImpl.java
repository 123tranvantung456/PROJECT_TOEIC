package com.javaweb.toeic.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.javaweb.toeic.converter.VocabularyConverter;
import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.entity.VocabularyEntity;
import com.javaweb.toeic.entity.VocabularyListEntity;
import com.javaweb.toeic.model.dto.VocabularyDTO;
import com.javaweb.toeic.model.response.VocabularyResponseDTO;
import com.javaweb.toeic.repository.UserRepository;
import com.javaweb.toeic.repository.VocabularyListRepository;
import com.javaweb.toeic.repository.VocabularyRepository;
import com.javaweb.toeic.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class VocabularyServiceImpl implements VocabularyService {
    @Autowired
    private VocabularyConverter vocabularyConverter;
    @Autowired
    private VocabularyRepository vocabularyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private VocabularyListRepository vocabularyListRepository;

    @Override
    public List<Long> getIdsVocabularyInList(Long listId) {
        List<Long> memorizedVocabularyIds = vocabularyRepository.findByUsers_Id(1L, listId);
        if (memorizedVocabularyIds.isEmpty()) {
            memorizedVocabularyIds.add(-1L);
        }

        return vocabularyRepository.
                findTop7ByIdNotInAndVocabularyList_IdOrderByIdAsc(memorizedVocabularyIds, listId);
    }

    @Override
    public VocabularyResponseDTO getVocabulary(Long vocabularyId) {
        return vocabularyConverter.toVocabularyResponseDTO
                (vocabularyRepository.findById(vocabularyId).orElse(null));
    }

    @Override
    public Long findVocabNextInStudyFlashcard(List<Long> vocabularyIds, Long listId) {
        vocabularyIds.addAll(vocabularyRepository.findByUsers_Id(1L, listId));
        return vocabularyRepository.findTop1ByIdNotInAndVocabularyList_IdOrderByIdAsc(vocabularyIds, listId);
    }

    @Override
    public void memoryVocabulary(Long id) {
        UserEntity userEntity = userRepository.findById(1L).orElse(null);
        VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id).orElse(null);
        vocabularyEntity.getUsers().add(userEntity);
        vocabularyRepository.save(vocabularyEntity);
    }

    @Override
    public List<Long> vocabMemory(Long listId) {
        return vocabularyRepository.findByUsers_Id(1L, listId);
    }

    @Override
    public VocabularyEntity createVocabulary(VocabularyDTO vocabularyDTO) {
        VocabularyEntity vocabularyEntity = vocabularyConverter.toVocabularyEntity(vocabularyDTO);

        // Upload ảnh lên Cloudinary nếu có
        MultipartFile imageFile = vocabularyDTO.getImage();
        if(imageFile.getOriginalFilename() == ""){
            vocabularyEntity.setImage(null);
        }
        if (vocabularyEntity.getDefinition().isEmpty()){
            vocabularyEntity.setDefinition(null);
        }
        if (vocabularyEntity.getPronunciation().isEmpty()){
            vocabularyEntity.setPronunciation(null);
        }
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                Map<String, Object> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                String imgUrl = (String) uploadResult.get("secure_url");
                vocabularyEntity.setImage(imgUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return vocabularyRepository.save(vocabularyEntity);
    }
}
