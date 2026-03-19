package com.UserService.UserService.Entity;


import jakarta.persistence.*;


@Entity
public class Users{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="userid_generator")
    @SequenceGenerator(name="userid_generator",sequenceName = "useridsequence",initialValue=1,allocationSize=5)
    int id;
    public  String username;
    public String password;
    public String roles;
    public String emailId;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(String srcAccount) {
        this.srcAccount = srcAccount;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    private String paymentMethod;
    private String srcAccount;
    private double availableAmount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}
