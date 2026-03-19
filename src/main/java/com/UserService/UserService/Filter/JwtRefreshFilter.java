package com.UserService.UserService.Filter;


import com.UserService.UserService.Entity.UserAuthEntity;
import com.UserService.UserService.RequestDTO.JwtAuthenticationToken;
import com.UserService.UserService.Utility.JWtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtRefreshFilter extends OncePerRequestFilter{

    private AuthenticationManager authManager;
    private JWtUtility jwtutility;

    public JwtRefreshFilter(AuthenticationManager authManager, JWtUtility jwtutility){
       this.authManager=authManager;
       this.jwtutility=jwtutility;
    }


    protected void  doFilterInternal(HttpServletRequest httpreq, HttpServletResponse httpresp, FilterChain filterChain)  throws ServletException, IOException
    {
       if(httpreq.getServletPath().equals("/refreshToken"))
       {
           filterChain.doFilter(httpreq,httpresp);
           return;
       }
       String refreshToken = extractJwtFormRequest(httpreq);

       JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(refreshToken);
       Authentication authResult =authManager.authenticate(jwtAuthenticationToken);
       if(authResult.isAuthenticated())
       {
           String newToken  = jwtutility.generateToken(authResult.getName(),15,(UserAuthEntity)authResult.getPrincipal());
           httpresp.setHeader("Authorization", "Bearer "+newToken);
       }
    }

    private String extractJwtFormRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies== null)
        {
            return null;
        }
        String refreshToken = null;
        for(Cookie cookie:cookies){
            if("tempToken".equals(cookie.getName()))
            {
                refreshToken =cookie.getValue();
            }
        }
        return refreshToken;
    }


}
