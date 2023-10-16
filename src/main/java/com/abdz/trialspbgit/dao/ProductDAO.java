package com.abdz.trialspbgit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Product;
import com.abdz.trialspbgit.enitity.Request;
import com.abdz.trialspbgit.enitity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ProductDAO {

    private EntityManager entityManager;

    @Autowired
    public ProductDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Product product)
    {
        entityManager.persist(product);
    }
    
    public Product findById(Integer id)
    {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findByUser(int uid)
    {
        TypedQuery<Product> q = entityManager.createQuery("From Product where uid=:uid", Product.class);
        q.setParameter("uid", uid);
        return q.getResultList();
    }

    public List<Product> findAll()
    {
        TypedQuery<Product> q = entityManager.createQuery("From Product", Product.class);
        
        return q.getResultList();
    }

    @Transactional
    public void update(Product product)
    {
        entityManager.merge(product);
    }

    @Transactional
    public void delete(int id)
    {
        Product product = entityManager.find(Product.class,id);
        entityManager.remove(product);
    }

    @Transactional
    public int deleteAll(){
        int numrows = entityManager.createQuery("Delete from Product", Product.class).executeUpdate();
        return numrows;
    }

}
