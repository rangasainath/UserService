package com.UserService.UserService.Service;


import com.UserService.UserService.Entity.User;
import com.UserService.UserService.Entity.UserAuthEntity;
import com.UserService.UserService.Repository.AuthenticationRepository;
import com.UserService.UserService.RequestDTO.UserAuthreqDTO;
import com.UserService.UserService.RequestDTO.userreqDTO;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    AuthenticationRepository authrepo;
    public UserAuthEntity saveUser(UserAuthreqDTO userauthreqdtO)
    {
        UserAuthEntity userauthentity = new UserAuthEntity();
//        userauthentity.setSrcAccount(userauthreqdtO.getSrcAccount());
        userauthentity.setRole(userauthreqdtO.getRole());
//        userauthentity.setAvailableAmount(userauthreqdtO.getAvailableAmount());
        userauthentity.setUsername(userauthreqdtO.getUsername());
        userauthentity.setPassword(userauthreqdtO.getPassword());
//        userauthentity.setPaymentMethod(userauthreqdtO.getPaymentMethod());
        userauthentity.setEmailid(userauthreqdtO.getEmailid());
         return authrepo.save(userauthentity);
    }

    @Override
    public UserAuthEntity loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserAuthEntity userauth  =  authrepo.findUserAuthEntityByUsername(username);
        if(userauth != null)
        {
            return  userauth;
        }
        else{
            throw new UsernameNotFoundException("usernotfound");
        }
    }
}
