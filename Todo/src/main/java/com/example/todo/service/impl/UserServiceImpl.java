package com.example.todo.service.impl;

import com.example.todo.domain.dto.request.UserRequestDto;
import com.example.todo.domain.dto.response.UserResponseDto;
import com.example.todo.domain.entity.User;
import com.example.todo.domain.mapper.request.UserRequestMapper;
import com.example.todo.domain.mapper.response.UserResponseMapper;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserRequestMapper userRequestMapper;

    @Autowired
    private final UserResponseMapper userResponseMapper;

    @Override
    public Page<UserResponseDto> getAllUser(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userResponseMapper::toResponseDto);
    }

    @Override
    public UserResponseDto getUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userResponseMapper.toResponseDto(userOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userRequestMapper.toEntity(userRequestDto);
        User user1 = userRepository.save(user);
        return userResponseMapper.toResponseDto(user1);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        Optional<User> userOptional = userRepository.findById(userRequestDto.getId());
        User user = new User();
        if (userOptional.isPresent()){
            user = userRepository.save(userRequestMapper.toEntity(userRequestDto));
        }else {
            throw new NullPointerException();
        }
        return userResponseMapper.toResponseDto(user);
    }

    @Override
    public void deleteUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        }else {
            throw new NullPointerException();
        }
    }

}
