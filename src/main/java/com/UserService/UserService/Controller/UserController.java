package com.UserService.UserService.Controller;


import com.UserService.UserService.DTO.UserDTO;
import com.UserService.UserService.Entity.User;
import com.UserService.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    public UserService userservice;

    @PostMapping("/api/v1/createUser/")
    public UserDTO createUser(@RequestBody UserDTO userdto)
    {
      return userservice.createUser(userdto);
    }

    @GetMapping("/api/v1/getUser")
    public User getUser(@RequestBody UserDTO userdto)
    {
        return  userservice.getUser(userdto);
    }

    @DeleteMapping("/api/v1/deleteUser")
    public User deleteUser(@RequestBody UserDTO userdto)
    {
        return userservice.deleteUser(userdto);
    }


}
