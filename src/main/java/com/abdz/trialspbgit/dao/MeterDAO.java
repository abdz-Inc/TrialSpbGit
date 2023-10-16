package com.abdz.trialspbgit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Meter;
import com.abdz.trialspbgit.enitity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    public Meter findById(Integer id)
    {
        return entityManager.find(Meter.class, id);
    }

    public List<Meter> findAll()
    {
        TypedQuery<Meter> q = entityManager.createQuery("From Meter", Meter.class);
        return q.getResultList();
    }
    
    @Transactional
    public void update(Meter meter)
    {
        entityManager.merge(meter);
    }

    @Transactional
    public void delete(int id)
    {
        Meter meter = entityManager.find(Meter.class,id);
        entityManager.remove(meter);
    }

    @Transactional
    public int deleteAll(){
        int numrows = entityManager.createQuery("Delete from Meter", Meter.class).executeUpdate();
        return numrows;
    }

}
