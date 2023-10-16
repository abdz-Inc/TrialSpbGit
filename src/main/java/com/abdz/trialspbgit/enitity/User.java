package com.abdz.trialspbgit.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {

    public User(){
        
    }

    public User(int mid, String walletaccntno, String username, String gmail, String description) {
        
        this.mid = mid;
        this.walletaccntno = walletaccntno;
        this.username = username;
        this.gmail = gmail;
        this.description = description;
    }

    @Id
    @Column(name="uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name="mid")
    private int mid;

    @Column(name="walletaccntno")
    private String walletaccntno;

    @Column(name="username")
    private String username;

    @Column(name="gmail")
    private String gmail;

    @Column(name="description")
    private String description;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getWalletaccntno() {
        return walletaccntno;
    }

    public void setWalletaccntno(String walletaccntno) {
        this.walletaccntno = walletaccntno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User [uid=" + uid + ", mid=" + mid + ", walletaccntno=" + walletaccntno + ", username=" + username
                + ", gmail=" + gmail + ", description=" + description + "]";
    }

    
    
}
