package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.model.request.RegisterRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;
    public UserEntity toUserEntity(RegisterRequestDTO registerRequestDTO){
        return modelMapper.map(registerRequestDTO, UserEntity.class);
    }
}
