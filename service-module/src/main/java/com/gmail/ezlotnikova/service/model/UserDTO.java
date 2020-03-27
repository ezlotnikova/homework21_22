package com.gmail.ezlotnikova.service.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.gmail.ezlotnikova.repository.model.constant.UserRoleEnum;

public class UserDTO {

    private Long id;
    @NotNull
    @NotEmpty(message = "Username required")
    @Size(min = 3, max = 40, message = "Username must be between 3 and 40 characters long")
    @Pattern(regexp = "[0-9a-zA-Z]+", message = "Username must contain only letters and numbers")
    private String username;
    @NotNull
    @NotEmpty(message = "Password required")
    private String password;
    @NotNull
    private UserRoleEnum role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

}