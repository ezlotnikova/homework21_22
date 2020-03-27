package com.gmail.ezlotnikova.service.impl;

import javax.transaction.Transactional;

import com.gmail.ezlotnikova.repository.UserRepository;
import com.gmail.ezlotnikova.repository.model.User;
import com.gmail.ezlotnikova.service.UserService;
import com.gmail.ezlotnikova.service.converter.UserConverter;
import com.gmail.ezlotnikova.service.exception.UserExistsException;
import com.gmail.ezlotnikova.service.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public void add(UserDTO userDTO) throws UserExistsException {
        if (userExists(userDTO.getUsername())) {
            throw new UserExistsException("User with username " + userDTO.getUsername() + " already exists");
        }
        User user = userConverter.getDatabaseObjectFromDTO(userDTO);
        userRepository.persist(user);
    }

    @Override
    @Transactional
    public UserDTO loadUserByUsername(String username) {
        User user = userRepository.loadUserByUsername(username);
        return userConverter.getDTOFromDatabaseObject(user);
    }

    private boolean userExists(String username) {
        User user = userRepository.loadUserByUsername(username);
        return user != null;
    }

}