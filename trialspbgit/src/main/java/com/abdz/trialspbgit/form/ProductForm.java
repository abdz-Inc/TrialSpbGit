package com.abdz.trialspbgit.form;

import jakarta.annotation.Nonnull;

public class ProductForm {
    
    @Nonnull
    private int rate;

    @Nonnull
    private int quantity;

    public ProductForm(int rate, int quantity) {
        this.rate = rate;
        this.quantity = quantity;
    }

    public ProductForm() {
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ProductForm [rate=" + rate + ", quantity=" + quantity + "]";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
