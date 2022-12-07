package com.example.todo.controller;

import com.example.todo.domain.dto.request.UserRequestDto;
import com.example.todo.domain.dto.response.UserResponseDto;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<Page<UserResponseDto>> getAllUser(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(userService.getAllUser(pageable));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(userService.getUserById(id));
    }

    @PostMapping("/create")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity
                .accepted()
                .body(userService.createUser(userRequestDto));
    }

    @PutMapping("/update")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity
                .accepted()
                .body(userService.updateUser(userRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
