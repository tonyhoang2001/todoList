package com.example.todo.domain.dto.response;

import com.example.todo.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String id;
    private String username;
    private Role role;
    private List<TodoResponseDto> todoList;
}
