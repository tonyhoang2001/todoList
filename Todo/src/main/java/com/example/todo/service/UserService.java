package com.example.todo.service;

import com.example.todo.domain.dto.request.UserRequestDto;
import com.example.todo.domain.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Page<UserResponseDto> getAllUser(Pageable pageable);

    UserResponseDto getUserById(String id);

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    void deleteUserById(String id);

    @Override
    UserDetails loadUserByUsername(String username);
}
