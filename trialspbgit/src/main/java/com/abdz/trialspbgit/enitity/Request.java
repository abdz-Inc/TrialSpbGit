package com.abdz.trialspbgit.enitity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="request")
public class Request {
    
    @Id
    @Column(name="rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    @Column(name="buyerid")
    private int buyerid;

    @Column(name="sellerid")
    private int sellerid;

    @Column(name="pid")
    private int pid;

    @Column(name="status")
    private String status;

    @Column(name="bidrate")
    private double bidrate;

    @Column(name="quantity")
    private int quantity;

    @Column(name="postdatetime")
    private Timestamp postdatetime;

    @Column(name="message")
    private String message;

    public Request(int buyerid, int sellerid, int pid, String status, double bidrate, int quantity,
            String message) {
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.pid = pid;
        this.status = status;
        this.bidrate = bidrate;
        this.quantity = quantity;
        this.message = message;
    }

    public Request() {
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(int buyerid) {
        this.buyerid = buyerid;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBidrate() {
        return bidrate;
    }

    public void setBidrate(double bidrate) {
        this.bidrate = bidrate;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Request [rid=" + rid + ", buyerid=" + buyerid + ", sellerid=" + sellerid + ", pid=" + pid + ", status="
                + status + ", bidrate=" + bidrate + ", quantity=" + quantity + ", postdatetime=" + postdatetime
                + ", message=" + message + "]";
    }
    
    

}
