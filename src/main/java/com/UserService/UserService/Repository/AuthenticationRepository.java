package com.UserService.UserService.Repository;

import com.UserService.UserService.Entity.User;
import com.UserService.UserService.Entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<UserAuthEntity,Integer>{
    public UserAuthEntity findUserAuthEntityByUsername(String username);
}
