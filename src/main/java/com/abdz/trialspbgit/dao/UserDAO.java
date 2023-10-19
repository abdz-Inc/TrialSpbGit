package com.abdz.trialspbgit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Power;
import com.abdz.trialspbgit.enitity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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

    public User findByWalletAccntNo(String walletaccntno) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.walletaccntno = :walletaccntno", User.class);
            query.setParameter("walletaccntno", walletaccntno);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the case when no user is found with the specified walletAccntNo
            return null;
        }
    }

}
