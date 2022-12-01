package com.example.todo.domain.dto.request;

import com.example.todo.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}
