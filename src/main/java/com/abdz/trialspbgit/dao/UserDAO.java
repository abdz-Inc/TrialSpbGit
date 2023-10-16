package com.abdz.trialspbgit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Power;
import com.abdz.trialspbgit.enitity.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UserDAO {
    
    private EntityManager entityManager;

    @Autowired
    public UserDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(User user)
    {
        entityManager.persist(user);
    }

    public User findById(Integer id)
    {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void update(User user)
    {
        entityManager.merge(user);
    }

    @Transactional
    public void delete(int id)
    {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Transactional
    public int deleteAll(){
        int numrows = entityManager.createQuery("Delete from User", User.class).executeUpdate();
        return numrows;
    }

}
