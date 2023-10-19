package com.abdz.trialspbgit.form;

import jakarta.annotation.Nonnull;

public class RegisterForm {
     @Nonnull
     int mid;
     @Nonnull
     String walletaccntno, username, gmail;
     
     String description;
     public RegisterForm(){

     }  
     public RegisterForm(  int mid,String walletaccntno,String username,String gmail){
          this.mid=mid;
          this.walletaccntno=walletaccntno;
          this.username=username;
          this.gmail=gmail;
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
          return "Mid:"+ mid+ "  \nWaNo:"+walletaccntno+" \nUsername:"+ username+ " \nGmail:" + gmail +"\nDesc:"+description;
     }
     
     
}
