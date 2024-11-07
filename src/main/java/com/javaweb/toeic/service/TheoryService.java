package com.javaweb.toeic.service;

import com.javaweb.toeic.model.response.TheoryCurrentResponseDTO;

public interface TheoryService {
    TheoryCurrentResponseDTO getTheoryCurrent(Long theoryId);
}
