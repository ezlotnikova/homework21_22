package com.gmail.ezlotnikova.service;

import com.gmail.ezlotnikova.service.exception.UserExistsException;
import com.gmail.ezlotnikova.service.model.UserDTO;

public interface UserService {

    void add(UserDTO user) throws UserExistsException;

    UserDTO loadUserByUsername(String username);

}