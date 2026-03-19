package com.UserService.UserService.RequestDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class userreqDTO {


    int id;
    String username;
    String password;
    String roles;
    String emailId;
    private String paymentMethod;
    private String srcAccount;

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(String srcAccount) {
        this.srcAccount = srcAccount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private double availableAmount;
            public int getId() {
                return id;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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
                emailId = emailId;
            }

}


