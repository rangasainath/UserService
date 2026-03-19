package com.UserService.UserService.TestSerivce;

import com.UserService.UserService.Entity.User;
import com.UserService.UserService.Repository.UserRepository;
import lombok.Data;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

//
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static reactor.core.publisher.Mono.when;

@DataJpaTest
public class TestService {
    @Autowired
    TestEntityManager testentitymanager;
    @Autowired
    UserRepository userrepository;


    public User setUpUserData(int id,String username, String password,String roles,String emailid,String paymentmethod,String srcaccount,double availamount)
    {
         User user = new User();
         user.setId(id);
         user.setAvailableAmount(availamount);
         user.setEmailId(emailid);
         user.setRoles(roles);
         user.setPaymentMethod(paymentmethod);
         user.setUsername(username);
         user.setPassword(password);
        return testentitymanager.persistAndFlush(user);


    }

    @BeforeAll()
    public static void BeforeAll()
    {

    }

    @AfterAll()
    public static void AfterAll()
    {

    }

    @BeforeEach()
    public void BeforeEach(){

    }

    @AfterEach()
    public void AfterEach()
    {

    }


    @Test
    public void createUser()
    {
        //Mock service
        User user = new User();
        user.setId(1);
        user.setAvailableAmount(1000);
        user.setEmailId("xyz@gmail.com");
        user.setRoles("dev");
        user.setPaymentMethod("online");
        user.setUsername("xyz");
        user.setPassword("qwert1234");
        //Exerscise Mock
        userrepository.save(user);
        //verfy
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isNotNull();

    }

    @Test
    public void UpdateUser()
    {
        //Mock
        User user = setUpUserData(1,"user1","234@#$567","roles","user1234@gmail.com","Onine","srcaccount",1000.00);
        User existingUser =(User) userrepository.findUserById(1);
        existingUser.setUsername("user1");
        existingUser.setPassword("234@#$567");
        existingUser.setRoles("dev");
        existingUser.setPaymentMethod("online");
        existingUser.setSrcAccount("srcaccount");
        existingUser.setEmailId("user1234");
        existingUser.setId(1);
        //Exerscise
        userrepository.save(user);
        //verify
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isNotNull();

    }

    @Test
    public void getUSer()
    {
        User user = setUpUserData(1,"user1","234@#$567","roles","user1234@gmail.com","Onine","srcaccount",1000.00);
//        User existingUser =(User) userrepository.findUserById(1);
//        existingUser.setUsername("user1");
//        existingUser.setPassword("234@#$567");
//        existingUser.setRoles("dev");
//        existingUser.setPaymentMethod("online");
//        existingUser.setSrcAccount("srcaccount");
////        existingUser.setEmailId("user1234");
//        existingUser.setId(1);
//        setup
//        User user = setUpUserData(1,"user1","234@#$567","roles","user1234@gmail.com","Onine","srcaccount",1000.00);
//          User existingUser = userrepository.findUserById(1);
//    Mock
//        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        Mockito.when(userrepository.findUserById(anyInt())).thenReturn(user);
//        Exerscise
        User existingUser = userrepository.findUserById(1);

//        verify
        assertThat(existingUser.getUsername()).isEqualTo("User1");
        assertThat(existingUser.getPassword()).isEqualTo("234@#$567");
        assertThat(existingUser.getRoles()).isEqualTo("dev");
        assertThat(existingUser.getPaymentMethod()).isEqualTo("online");
        assertThat(existingUser.getSrcAccount()).isEqualTo("srcaccount");
        assertThat(existingUser.getEmailId()).isEqualTo("user1234@gmail.com");
        assertThat(existingUser.getId()).isEqualTo("1");

    }

    @Test
    public void deleteUser()
    {
//SetUp
        User user = setUpUserData(1,"user1","234@#$567","roles","user1234@gmail.com","Onine","srcaccount",1000.00);

        //Exerscise
        Optional<User> existingUser = userrepository.findById(1);
        //verify
         if(existingUser.isPresent())
         {
             userrepository.deleteById(user.getId());
         }

    }
}
