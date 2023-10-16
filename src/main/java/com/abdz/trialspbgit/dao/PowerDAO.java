package com.abdz.trialspbgit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Meter;
import com.abdz.trialspbgit.enitity.Power;
import com.abdz.trialspbgit.enitity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    public Power findById(Integer id)
    {
        return entityManager.find(Power.class, id);
    }

    public List<Power> findByIdAndMode(Integer id, String mode){
        TypedQuery<Power> query = entityManager.createQuery("From Power where mid =:id and mode=:mode", Power.class);
        query.setParameter("id",id);
        query.setParameter("mode", mode);
        return query.getResultList();
    }

    @Transactional
    public void update(Power power)
    {
        entityManager.merge(power);
    }

    @Transactional
    public void delete(int id)
    {
        Power power = entityManager.find(Power.class,id);
        entityManager.remove(power);
    }

    @Transactional
    public int deleteAll(){
        int numrows = entityManager.createQuery("Delete from Power", Power.class).executeUpdate();
        return numrows;
    }
    
}
