package com.abdz.trialspbgit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Request;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class RequestDAO {

    private EntityManager entityManager;

    @Autowired
    public RequestDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Request request)
    {
        entityManager.persist(request);
    }
    
}
