package com.abdz.trialspbgit.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="smartmeter")
public class Meter {
    
     @Id
    @Column(name="mid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid;

    @Column(name="capacity")
    private int capacity;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="phnno")
    private String phnno;

    public Meter(int capacity, String name, String address, String phnno) {
        this.capacity = capacity;
        this.name = name;
        this.address = address;
        this.phnno = phnno;
    }

    public Meter() {
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhnno() {
        return phnno;
    }

    public void setPhnno(String phnno) {
        this.phnno = phnno;
    }

    

}
