package com.example.userservicenov24.dtos;

import com.example.userservicenov24.models.Role;
import com.example.userservicenov24.models.User;

import java.util.List;

public class UserResponseDto {
    private String name;
    private String email;
    private List<Role> roleList;

    public static UserResponseDto from(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setName(user.getName());
        responseDto.setRoleList(user.getRoles());

        return responseDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
