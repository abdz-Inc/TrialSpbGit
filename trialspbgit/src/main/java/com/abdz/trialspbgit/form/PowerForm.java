package com.abdz.trialspbgit.form;

import jakarta.annotation.Nonnull;

public class PowerForm {

    @Nonnull
    private int mid;

    @Nonnull
    private int quantity;

    @Nonnull
    private String mode;

    public PowerForm() {
    }

    public PowerForm(int mid, int quantity, String mode) {
        this.mid = mid;
        this.quantity = quantity;
        this.mode = mode;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "PowerForm [mid=" + mid + ", quantity=" + quantity + ", mode=" + mode + "]";
    }

    
    
}
