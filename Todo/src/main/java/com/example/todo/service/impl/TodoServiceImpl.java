package com.example.todo.service.impl;

import com.example.todo.domain.dto.request.TodoRequestDto;
import com.example.todo.domain.dto.response.TodoResponseDto;
import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.entity.User;
import com.example.todo.domain.mapper.request.TodoRequestMapper;
import com.example.todo.domain.mapper.response.TodoResponseMapper;
import com.example.todo.repository.TodoRepository;
import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    @Autowired
    private final TodoRepository todoRepository;

    @Autowired
    private final TodoRequestMapper todoRequestMapper;

    @Autowired
    private final TodoResponseMapper todoResponseMapper;

    @Override
    public Page<TodoResponseDto> getAllTodo(Pageable pageable) {
        Page<Todo> todoPage = todoRepository.findAll(pageable);
        return todoPage.map(todoResponseMapper::toResponseDto);
    }

    @Override
    public TodoResponseDto getTodoById(String id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return todoResponseMapper.toResponseDto(todoOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto, String userID) {
        Todo todo = todoRequestMapper.toEntity(todoRequestDto);
        User user = new User();
        user.setId(userID);
        todo.setUser(user);
        Todo todoAfter = todoRepository.save(todo);
        return todoResponseMapper.toResponseDto(todoAfter);
    }

    @Override
    public TodoResponseDto updateTodo(TodoRequestDto todoRequestDto) {
        Optional<Todo> todoOptional = todoRepository.findById(todoRequestDto.getId());
        Todo todo = new Todo();
        if (todoOptional.isPresent()) {
            todo = todoRepository.save(todoRequestMapper.toEntity(todoRequestDto));
        } else {
            throw new NullPointerException();
        }

        return todoResponseMapper.toResponseDto(todo);
    }

    @Override
    public void deleteTodoById(String id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            todoRepository.delete(todoOptional.get());
        } else {
            throw new NullPointerException();
        }
    }
}
