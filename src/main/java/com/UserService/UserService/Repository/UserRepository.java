package com.UserService.UserService.Repository;

import com.UserService.UserService.Entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
  public User getUserByEmailId(String email);
  public User findUserByEmailId(String email);
  public User findUserById(int id);
}
