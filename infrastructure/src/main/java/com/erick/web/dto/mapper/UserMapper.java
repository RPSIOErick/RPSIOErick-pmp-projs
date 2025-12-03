package com.erick.web.dto.mapper;

import com.erick.entity.User;
import com.erick.web.dto.CreateDto;
import org.modelmapper.ModelMapper;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(CreateDto dto) {
        return new ModelMapper().map(dto, User.class);
    }

}
