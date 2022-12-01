package com.example.todo.service;

import com.example.todo.domain.dto.request.TodoRequestDto;
import com.example.todo.domain.dto.response.TodoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {
    Page<TodoResponseDto> getAllTodo(Pageable pageable);
    TodoResponseDto getTodoById(String id);
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto, String userID);
    TodoResponseDto updateTodo(TodoRequestDto todoRequestDto);
    void deleteTodoById(String id);
}
