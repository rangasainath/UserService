package com.UserService.UserService.Filter;






import com.UserService.UserService.RequestDTO.JwtAuthenticationToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public  class JwtValidationFilter extends OncePerRequestFilter {

    @Autowired
    private  AuthenticationManager authmanager;

    public JwtValidationFilter(AuthenticationManager authmanager)
    {
     this.authmanager=authmanager;
    }

    protected void doFilterInternal(HttpServletRequest httpreq, HttpServletResponse httpresp, FilterChain filterchain)throws ServletException, IOException
    {
        String token = extractJwtFormRequest(httpreq);
        if(token !=null)
        {
           JwtAuthenticationToken jwtauthtoken = new JwtAuthenticationToken(token);
           Authentication authResult =authmanager.authenticate(jwtauthtoken);
           if(authResult.isAuthenticated())
           {
               SecurityContextHolder.getContext().setAuthentication(authResult);
           }
        }
         filterchain.doFilter(httpreq,httpresp);

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
