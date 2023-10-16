package com.abdz.trialspbgit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Request;
import com.abdz.trialspbgit.enitity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    
    public Request findById(Integer id)
    {
        return entityManager.find(Request.class, id);
    }

    //find all recieved requests
    public List<Request> findAllRecievedRequest(Integer id)
    {
        TypedQuery<Request> q = entityManager.createQuery("From Request where sellerid=:id", Request.class);
        q.setParameter("id",id);
        return q.getResultList();
    }

    //find all sent requests
    public List<Request> findAllSentRequest(Integer id)
    {
        TypedQuery<Request> q = entityManager.createQuery("From Request where buyerid=:id", Request.class);
        q.setParameter("id",id);
        return q.getResultList();
    }

    @Transactional
    public void update(Request request)
    {
        entityManager.merge(request);
    }

    @Transactional
    public void delete(int id)
    {
        Request request = entityManager.find(Request.class,id);
        entityManager.remove(request);
    }

    @Transactional
    public int deleteAll(){
        int numrows = entityManager.createQuery("Delete from Request", Request.class).executeUpdate();
        return numrows;
    }

}
