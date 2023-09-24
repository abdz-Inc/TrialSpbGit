package com.abdz.trialspbgit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Power;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class PowerDAO {

    private EntityManager entityManager;

    @Autowired
    public PowerDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Power power)
    {
        entityManager.persist(power);
    }
    
}
