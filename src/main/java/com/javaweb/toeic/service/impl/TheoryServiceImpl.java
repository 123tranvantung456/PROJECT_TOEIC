package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.TheoryConverter;
import com.javaweb.toeic.entity.TheoryEntity;
import com.javaweb.toeic.model.response.TheoryCurrentResponseDTO;
import com.javaweb.toeic.repository.TheoryRepository;
import com.javaweb.toeic.service.TheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheoryServiceImpl implements TheoryService {
    @Autowired
    private TheoryRepository theoryRepository;
    @Autowired
    private TheoryConverter theoryConverter;
    @Override
    public TheoryCurrentResponseDTO getTheoryCurrent(Long theoryId) {
        TheoryEntity theoryEntities = theoryRepository.findById(theoryId).get();
        return theoryConverter.toTheoryCurrentResponseDTO(theoryEntities);
    }

}
