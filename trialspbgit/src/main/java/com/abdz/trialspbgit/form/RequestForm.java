package com.abdz.trialspbgit.form;
import jakarta.annotation.Nonnull;

public class RequestForm {

    @Nonnull
    private int quantity;

    private String message;

    @Nonnull
    private double rate;

    @Nonnull
    private int pid;

    public RequestForm() {
    }

    public RequestForm(int quantity, String message, double rate, int pid) {
        this.quantity = quantity;
        this.message = message;
        this.rate = rate;
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "RequestForm [quantity=" + quantity + ", message=" + message + ", rate=" + rate + ", pid=" + pid + "]";
    }

    
    
    
}
