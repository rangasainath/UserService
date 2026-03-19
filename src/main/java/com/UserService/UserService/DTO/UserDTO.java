package com.UserService.UserService.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    int id;
    String username;
    String password;
    String roles;
    String emailId;

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
