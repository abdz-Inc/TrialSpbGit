package com.abdz.trialspbgit.form;

import jakarta.annotation.Nonnull;

public class SmartMeterForm {
    
    @Nonnull
    private String name;

    @Nonnull
    private String phnno;

    @Nonnull
    private String address;

    @Nonnull
    private int capacity;

    public SmartMeterForm(String name, String phnno, String address, int capacity) {
        this.name = name;
        this.phnno = phnno;
        this.address = address;
        this.capacity = capacity;
    }

    public SmartMeterForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnno() {
        return phnno;
    }

    public void setPhnno(String phnno) {
        this.phnno = phnno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "SmartMeterForm [name=" + name + ", phnno=" + phnno + ", address=" + address + ", capacity=" + capacity
                + "]";
    }

}
