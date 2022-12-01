package com.example.todo.domain.mapper.request;

import com.example.todo.domain.dto.request.TodoRequestDto;
import com.example.todo.domain.entity.Todo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TodoRequestMapper {
    TodoRequestDto toRequestDto(Todo todo);

    Todo toEntity(TodoRequestDto todoRequestDto);
}
