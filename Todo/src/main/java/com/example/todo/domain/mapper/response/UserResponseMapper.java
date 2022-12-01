package com.example.todo.domain.mapper.response;

import com.example.todo.domain.dto.response.UserResponseDto;
import com.example.todo.domain.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserResponseMapper {
    UserResponseDto toResponseDto(User user);

    User toEntity(UserResponseDto userResponseDto);
}
