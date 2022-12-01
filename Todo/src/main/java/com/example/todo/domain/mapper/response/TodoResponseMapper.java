package com.example.todo.domain.mapper.response;

import com.example.todo.domain.dto.response.TodoResponseDto;
import com.example.todo.domain.entity.Todo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TodoResponseMapper {
    TodoResponseDto toResponseDto(Todo todo);

    Todo toEntity(TodoResponseDto todoResponseDto);
}
