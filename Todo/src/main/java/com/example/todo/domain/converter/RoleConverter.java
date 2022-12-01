package com.example.todo.domain.converter;

import com.example.todo.domain.enums.Role;

import javax.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<Role,String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(String s) {
        return Role.of(s);
    }
}
