package com.example.todo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private String value;

    private static final Map<String, Role> roleMap  = new HashMap<>();

    static {
        for (Role role : Role.values()){
            roleMap.put(role.value, role);
        }
    }

    public static Role of(String s){
        return roleMap.get(s);
    }

}
