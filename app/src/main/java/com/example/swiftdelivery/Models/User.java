package com.example.swiftdelivery.Models;

public class User {
   public String userName, mail, password, userType;

    public User(String userName, String mail, String password, String userType) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userType = userType;
    }

    public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
