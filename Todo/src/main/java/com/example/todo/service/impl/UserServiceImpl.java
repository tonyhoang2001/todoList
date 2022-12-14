package com.example.todo.service.impl;

import com.example.todo.domain.dto.request.UserRequestDto;
import com.example.todo.domain.dto.response.UserResponseDto;
import com.example.todo.domain.entity.User;
import com.example.todo.domain.mapper.request.UserRequestMapper;
import com.example.todo.domain.mapper.response.UserResponseMapper;
import com.example.todo.jwt.CustomUserDetails;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private PasswordEncoder encoder;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
        encoder = passwordEncoder();
        User user = userRequestMapper.toEntity(userRequestDto);
        user.setPassword(encoder.encode(user.getPassword()));
        User user1 = userRepository.save(user);
        return userResponseMapper.toResponseDto(user1);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        encoder = passwordEncoder();
        Optional<User> userOptional = userRepository.findById(userRequestDto.getId());
        User updatedUser = new User();
        if (userOptional.isPresent()) {
            User user = userRequestMapper.toEntity(userRequestDto);
            user.setPassword(encoder.encode(user.getPassword()));
            updatedUser = userRepository.save(user);
        } else {
            throw new NullPointerException();
        }
        return userResponseMapper.toResponseDto(updatedUser);
    }

    @Override
    public void deleteUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

}
