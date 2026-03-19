package com.UserService.UserService.RespDTO;

import com.UserService.UserService.DTO.UserDTO;
import com.UserService.UserService.Entity.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class userrespDTO
{
        int id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User user;
//    public  String Error;


//        public int getId() {
//            return id;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getRoles() {
//            return roles;
//        }
//
//        public void setRoles(String roles) {
//            this.roles = roles;
//        }
//
//        public String getEmailId() {
//            return emailId;
//        }
//
//        public void setEmailId(String emailId) {
//            emailId = emailId;
//        }
}
