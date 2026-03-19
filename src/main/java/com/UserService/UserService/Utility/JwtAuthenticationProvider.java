package com.UserService.UserService.Utility;


import com.UserService.UserService.RequestDTO.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JWtUtility jwtutility;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtAuthenticationProvider(JWtUtility jwtutility, UserDetailsService userdetailsservice) {
        this.jwtutility=jwtutility;
        this.userDetailsService=userdetailsservice;
    }

    @Override
    public boolean supports(Class<?> authentication){
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

   @Override
    public Authentication authenticate(Authentication auth)
   {
       String token = ((JwtAuthenticationToken)auth).getToken();
       String username  = jwtutility.validateandExtractUsername(token);
      if(username == null)
      {
          throw new BadCredentialsException("Invalid JwT token");
      }
       UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
   }
}
