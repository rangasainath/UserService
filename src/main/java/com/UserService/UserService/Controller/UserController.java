package com.UserService.UserService.Controller;


import com.UserService.UserService.DTO.UserDTO;
import com.UserService.UserService.Entity.User;
import com.UserService.UserService.RequestDTO.userreqDTO;
import com.UserService.UserService.RespDTO.userrespDTO;
import com.UserService.UserService.Service.UserService;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class UserController {

    @Autowired
    public UserService userservice;

    @PostMapping("/api/v1/createuser")
    public userrespDTO createUser(Locale locale, @RequestBody userreqDTO userreqdto)
    {
        locale.
      return userservice.createUser(userreqdto);
    }

//    @GetMapping("/api/v1/getUser")
//    public userrespDTO getUser(@RequestBody userreqDTO userreqdto)
//    {
@GetMapping("/api/v1/getUser/{id}")
public User getUser(@PathVariable Integer id)
{

//        return  userservice.getUser(userreqdto);
    return  userservice.getUser(id);
    }


    @PutMapping("api/v1/updateuser")
    public User updateUser(@RequestBody userreqDTO userreqdto)
    {
        return userservice.updateUser(userreqdto);
    }
    @PutMapping("/api/v1/updateUserAmount/")
    public User updateUserAmount(@RequestParam(value="id",required=true)int id,@RequestParam(value="amount",required=true)double amount)
    {

//        return  userservice.getUser(userreqdto);
        return  userservice.updateUserAmount(id,amount);
    }
    @DeleteMapping("/api/v1/deleteUser")
    public userrespDTO deleteUser(@RequestBody userreqDTO userreqdto)
    {
        return userservice.deleteUser(userreqdto);
    }
}
