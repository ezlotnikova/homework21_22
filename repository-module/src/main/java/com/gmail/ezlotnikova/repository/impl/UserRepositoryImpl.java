package com.gmail.ezlotnikova.repository.impl;

import java.lang.invoke.MethodHandles;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.gmail.ezlotnikova.repository.UserRepository;
import com.gmail.ezlotnikova.repository.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public User loadUserByUsername(String username) {
        String hql = "FROM User as U WHERE U.username =:username";
        Query query = entityManager.createQuery(hql);
        query.setParameter("username", username);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}