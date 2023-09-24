package com.abdz.trialspbgit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Meter;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class MeterDAO {

    private EntityManager entityManager;

    @Autowired
    public MeterDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Meter meter)
    {
        entityManager.persist(meter);
    }
    
}
