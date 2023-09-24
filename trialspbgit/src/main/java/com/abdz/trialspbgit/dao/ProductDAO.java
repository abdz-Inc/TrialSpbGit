package com.abdz.trialspbgit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abdz.trialspbgit.enitity.Product;
import com.abdz.trialspbgit.enitity.User;

import jakarta.persistence.EntityManager;
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

}
