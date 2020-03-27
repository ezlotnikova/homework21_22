package com.gmail.ezlotnikova.repository;

import com.gmail.ezlotnikova.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User> {

    User loadUserByUsername(String username);

}