package com.abdz.trialspbgit.enitity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
    
    @Id
    @Column(name="pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name="uid")
    private int uid;

    @Column(name="rate")
    private double rate;

    @Column(name="quantity")
    private int quantity;

    @Column(name="postdatetime")
    private Timestamp postdatetime;

    public Product(int uid, double rate, int quantity, Timestamp postdatetime) {
        this.uid = uid;
        this.rate = rate;
        this.quantity = quantity;
        this.postdatetime = postdatetime;
    }

    public Product() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getPostdatetime() {
        return postdatetime;
    }

    public void setPostdatetime(Timestamp postdatetime) {
        this.postdatetime = postdatetime;
    }

    @Override
    public String toString() {
        return "Product [pid=" + pid + ", uid=" + uid + ", rate=" + rate + ", quantity=" + quantity + ", postdatetime="
                + postdatetime + "]";
    }

    
    

}
