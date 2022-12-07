package com.example.todo.controller;

import com.example.todo.domain.dto.request.TodoRequestDto;
import com.example.todo.domain.dto.response.TodoResponseDto;
import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/v1/api/todo")
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    private final TodoService todoService;

    @GetMapping("")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<Page<TodoResponseDto>> getAllTodoList(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(todoService.getAllTodo(pageable));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(todoService.getTodoById(id));
    }

    @PostMapping("/create")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto todoRequestDto,
                                                      @PathVariable String userID) {
        return ResponseEntity
                .accepted()
                .body(todoService.createTodo(todoRequestDto, userID));
    }

    @PutMapping("/update")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<TodoResponseDto> updateTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return ResponseEntity
                .accepted()
                .body(todoService.updateTodo(todoRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<?> deleteTodoById(@PathVariable String id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
