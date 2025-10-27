package com.UserService.UserService.Service;

import com.UserService.UserService.DTO.UserDTO;
import com.UserService.UserService.Entity.User;
import com.UserService.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userrepository;
    public UserDTO createUser(UserDTO userdto) {
        User user = new User();

//          user.setId(userdto.getId());
          user.setPassword(userdto.getPassword());
          user.setUsername(userdto.getUsername());
          user.setEmailId(userdto.getEmailId());
          user.setRoles(userdto.getRoles());
          userrepository.save(user);
          return userdto;
    }
    public User getUser(UserDTO userdto)
    {
        User user = new User();
//        UserDTO userdtobject = new UserDTO();
     user=userrepository.getUserByEmailId(userdto.getEmailId());
        return user;

    }
    public User deleteUser(UserDTO userdto)
    {
        User result = userrepository.findUserByEmailId(userdto.getEmailId());
        if(result != null)
        {
            userrepository.deleteById(result.getId());
        }
        return result;
    }

}
