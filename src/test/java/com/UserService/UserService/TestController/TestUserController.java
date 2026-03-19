package com.UserService.UserService.TestController;


import com.UserService.UserService.Entity.User;
import com.UserService.UserService.RequestDTO.userreqDTO;
import com.UserService.UserService.RespDTO.userrespDTO;
import com.UserService.UserService.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@WebMvcTest()
@AutoConfigureMockMvc(addFilters=false)
public class TestUserController
{
    @Autowired
    MockMvc mockmvc;
    @MockitoBean
    UserService userService;

    @Autowired
    MockMvcRequestBuilders mockmvcreqbuilder;
  @Autowired
  MockMvcResultMatchers mockmvcresultmatcher;
    @Autowired
    ObjectMapper objectmapper;


    @BeforeAll()
    public static void setUp()
    {
    //add dependency for MockMvcsecurity builder.

    }

    @BeforeEach()
    public void beforeEach(){


    }

    @AfterEach()
    public void afterEach(){

    }

    @AfterAll()
    public static void Collapse()
    {

    }


    @Test
    public void testcreateUser() throws Exception
    {
        //initilize parameters requireed to mock the service.
        userreqDTO reqdto = new userreqDTO();
        reqdto.setAvailableAmount(1000);
        reqdto.setEmailId("punnasainath@gmail.com");
        reqdto.setPassword("Star123");
        reqdto.setRoles("dev");
        reqdto.setPaymentMethod("online");
        reqdto.setSrcAccount("bank");
        reqdto.setUsername("user123");
        reqdto.setId(1);
       //respone dto
        User user  = new User();
        user.setAvailableAmount(1000);
        user.setEmailId("punnasainath@gmail.com");
        user.setPassword("Star123");
        user.setRoles("dev");
        user.setPaymentMethod("online");
        user.setSrcAccount("bank");
        user.setUsername("user123");
        user.setId(1);
        userrespDTO respdto = new userrespDTO();
        respdto.setMessage("usercreatedsuccesfully");

        respdto.setUser(user);

        //Mock
//         when(userService.createUser(any(userreqDTO.class))).thenReturn(respdto);
        Mockito.when(userService.createUser(any(userreqDTO.class))).thenReturn(respdto);
        //Exescise
        mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/createuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectmapper.writeValueAsString(reqdto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.taskName",is("user123")));

//                .andExpect(jsonPath("$.username").exists());
        //veify
        verify(userService).createUser(reqdto);

    }

    @Test
    public void testgetUser() throws Exception
    {
        User user = new User();
        user.setId(1);
        user.setAvailableAmount(1000);
        user.setEmailId("punnasainath@gmail.com");
        user.setPassword("Star123");
        user.setRoles("dev");
        user.setPaymentMethod("online");
        user.setSrcAccount("bank");
        user.setUsername("user123");
        userrespDTO respdto = new userrespDTO();
        respdto.setUser(user);

        int id=1;
        //config Mock
          Mockito.when(userService.getUser(any(Integer.class))).thenReturn(respdto.getUser());
        //Exercise mock
        mockmvc.perform(MockMvcRequestBuilders.get("api/v1/getUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userid","1"))
                .andDo(print())
                .andExpect(status().isOk())
                        .andExpect((ResultMatcher)jsonPath("$.username").exists());
       //verify mock
        Mockito.verify(userService).getUser(any(Integer.class));

    }

    @Test
    public void testupdateUser() throws Exception
    {  //Request DTO
        userreqDTO reqdto = new userreqDTO();
        reqdto.setAvailableAmount(1000);
        reqdto.setEmailId("punnasainath@gmail.com");
        reqdto.setPassword("Star123");
        reqdto.setRoles("dev");
        reqdto.setPaymentMethod("online");
        reqdto.setSrcAccount("bank");
        reqdto.setUsername("user123");
        reqdto.setId(1);
        //response DTO
        User user = new User();
        user.setId(1);
        user.setAvailableAmount(1000);
        user.setEmailId("punnasainath@gmail.com");
        user.setPassword("Star123");
        user.setRoles("dev");
        user.setPaymentMethod("online");
        user.setSrcAccount("bank");
        user.setUsername("user123");
        userrespDTO respdto = new userrespDTO();
        respdto.setUser(user);
        //when Mock
          Mockito.when(userService.getUser(any(Integer.class))).thenReturn(user);
        //Exerscise Mock
           mockmvc.perform(
                   MockMvcRequestBuilders
                   .put("/api/v1/updateUser")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(objectmapper.writeValueAsString(reqdto)))
                   .andDo(print())
                   .andExpect(status().isOk())
                   .andExpect((ResultMatcher)jsonPath("$.username")
                   .exists());

        //verify
         Mockito.verify(userService).updateUser(reqdto);
    }

    @Test
    public void testdeleteUser() throws Exception
    {
        //reqDTO
        userreqDTO reqdto = new userreqDTO();
        reqdto.setAvailableAmount(1000);
        reqdto.setEmailId("punnasainath@gmail.com");
        reqdto.setPassword("Star123");
        reqdto.setRoles("dev");
        reqdto.setPaymentMethod("online");
        reqdto.setSrcAccount("bank");
        reqdto.setUsername("user123");
        reqdto.setId(1);
        //response DTO
        User user = new User();
        user.setId(1);
        user.setAvailableAmount(1000);
        user.setEmailId("punnasainath@gmail.com");
        user.setPassword("Star123");
        user.setRoles("dev");
        user.setPaymentMethod("online");
        user.setSrcAccount("bank");
        user.setUsername("user123");
        userrespDTO respdto = new userrespDTO();
        respdto.setUser(user);
        //Mock
        Mockito.when(userService.deleteUser(any(userreqDTO.class))).thenReturn(respdto);
        //Exerscise
        mockmvc.perform(MockMvcRequestBuilders.delete("api/v1/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectmapper.writeValueAsString(reqdto)))
                .andDo(print())
                .andExpect(status().isOk());
        //verify
        Mockito.verify(userService).deleteUser(reqdto);

    }

}
