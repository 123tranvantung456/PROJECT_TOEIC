package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.TheoryEntity;
import com.javaweb.toeic.model.response.TheoryCurrentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TheoryConverter {
    @Autowired
    private ModelMapper modelMapper;
    public TheoryCurrentResponseDTO toTheoryCurrentResponseDTO(TheoryEntity theoryEntity){
        return modelMapper.map(theoryEntity, TheoryCurrentResponseDTO.class);
    }

}
