package com.gmail.ezlotnikova.service.converter.impl;

import com.gmail.ezlotnikova.repository.model.User;
import com.gmail.ezlotnikova.repository.model.constant.UserRoleEnum;
import com.gmail.ezlotnikova.service.converter.UserConverter;
import com.gmail.ezlotnikova.service.model.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserConverterImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User getDatabaseObjectFromDTO(UserDTO userDTO) {
        User user = new User();
        String username = userDTO.getUsername();
        user.setUsername(username);
        String password = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(password);
        UserRoleEnum role = userDTO.getRole();
        user.setRole(role);
        return user;
    }

    @Override
    public UserDTO getDTOFromDatabaseObject(User user) {
        UserDTO userDTO = new UserDTO();
        Long id = user.getId();
        userDTO.setId(id);
        String username = user.getUsername();
        userDTO.setUsername(username);
        String password = user.getPassword();
        userDTO.setPassword(password);
        UserRoleEnum role = user.getRole();
        userDTO.setRole(role);
        return userDTO;
    }

}