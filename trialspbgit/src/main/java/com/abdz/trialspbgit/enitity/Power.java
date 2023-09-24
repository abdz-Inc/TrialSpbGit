package com.abdz.trialspbgit.enitity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="power")
public class Power {
     @Id
    @Column(name="pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name="mid")
    private int mid;

    @Column(name="mode")
    private String mode;

    @Column(name="quantity")
    private int quantity;

    @Column(name="postdatetime")
    private Timestamp postdatetime;

    public Power(int mid, String mode, int quantity, Timestamp postdatetime) {
        this.mid = mid;
        this.mode = mode;
        this.quantity = quantity;
        this.postdatetime = postdatetime;
    }

    public Power() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    
}
