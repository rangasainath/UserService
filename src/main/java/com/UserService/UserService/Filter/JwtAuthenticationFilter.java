package com.UserService.UserService.Filter;

import com.UserService.UserService.Entity.UserAuthEntity;
import com.UserService.UserService.RequestDTO.LoginRequest;
import com.UserService.UserService.Utility.JWtUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {




private  AuthenticationManager authmanager;
private JWtUtility jwtutility;


public JwtAuthenticationFilter(AuthenticationManager authmanager,JWtUtility jwtuitlity)
 {
     this.authmanager=authmanager;
     this.jwtutility=jwtutility;
 }


    @Override
    public void doFilterInternal(HttpServletRequest httpreq, HttpServletResponse httpresp, FilterChain filterChain) throws ServletException, IOException
    {
      if( !httpreq.getServletPath().equals("/generate-token")){
          filterChain.doFilter(httpreq,httpresp);
          return;
      }

        ObjectMapper objectamapper = new ObjectMapper();
        var customloginrequest =httpreq.getInputStream();
        LoginRequest loginrequest = objectamapper.readValue(customloginrequest, LoginRequest.class);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginrequest.getUsername(),loginrequest.getPassword());
        Authentication authresult = authmanager.authenticate(auth);



        if(authresult.isAuthenticated()) {
            String token = jwtutility.generateToken(authresult.getName(), 15, (UserAuthEntity) authresult.getPrincipal());
            //setting temporary token.
            httpresp.setHeader("Authorization", "Bearer " + token);
            Cookie temporaryCookie = new Cookie("tempToken", token);
            temporaryCookie.setHttpOnly(true);
            temporaryCookie.setSecure(true);
            temporaryCookie.setPath("/");
            temporaryCookie.setMaxAge(15 * 60);
            httpresp.addCookie(temporaryCookie);
            //setting refresh token.
            String refreshToken = jwtutility.generateToken(authresult.getName(), 7 * 24 * 60, (UserAuthEntity) authresult.getPrincipal());
            Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
            refreshCookie.setHttpOnly(true);
            refreshCookie.setSecure(true);
            refreshCookie.setPath("/");
            refreshCookie.setMaxAge(7 * 24 * 60 * 60);
            httpresp.addCookie(refreshCookie);
//            response.sendRedirect("/getdata");
        }
    }

}

