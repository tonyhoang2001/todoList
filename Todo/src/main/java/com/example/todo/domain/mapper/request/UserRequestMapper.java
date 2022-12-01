package com.example.todo.domain.mapper.request;

import com.example.todo.domain.dto.request.UserRequestDto;
import com.example.todo.domain.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserRequestMapper {
    UserRequestDto toRequestDto(User user);

    User toEntity(UserRequestDto userRequestDto);
}
