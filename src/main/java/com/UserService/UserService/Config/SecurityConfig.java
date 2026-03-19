package com.UserService.UserService.Config;






import com.UserService.UserService.Filter.JwtAuthenticationFilter;

import com.UserService.UserService.Filter.JwtRefreshFilter;

import com.UserService.UserService.Filter.JwtValidationFilter;



import com.UserService.UserService.Utility.JWtUtility;

import com.UserService.UserService.Utility.JwtAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpRequest;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.ProviderManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.filter.OncePerRequestFilter;



import javax.xml.crypto.Data;

import java.net.http.HttpResponse;

import java.util.Arrays;



@Configuration

@EnableWebSecurity

public class SecurityConfig {





    @Autowired

    private JWtUtility jwtutility;



    @Autowired

    private UserDetailsService userdetailsservice;



    @Autowired

    public SecurityConfig(JWtUtility jwtutility, UserDetailsService userdetailsservice)

    {

        this.jwtutility = jwtutility;

        this.userdetailsservice= userdetailsservice;

    }

    @Bean

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }



    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{



        JwtValidationFilter jwtvalidfilter = new JwtValidationFilter(authManager);
        JwtRefreshFilter jwtrefreshfilter = new JwtRefreshFilter(authManager,jwtutility);
        JwtAuthenticationFilter jwtauthfilter = new JwtAuthenticationFilter(authManager,jwtutility);
        http.authorizeHttpRequests(   (auth)->

                        auth.requestMatchers("/api/signup").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/directtologinform").permitAll()
                                .requestMatchers("/directtosignup").permitAll()
                                .requestMatchers("/api/logout").permitAll())



                .csrf(csrf-> csrf.disable())

                .addFilterBefore(jwtauthfilter, UsernamePasswordAuthenticationFilter.class)

                .addFilterAfter(jwtvalidfilter,JwtAuthenticationFilter.class)

                .addFilterAfter(jwtrefreshfilter, JwtValidationFilter.class);

        return http.build();



    }



    @Bean

    public DaoAuthenticationProvider daoAuthenticationProvider()

    {

        DaoAuthenticationProvider dAuthenticationProvider= new DaoAuthenticationProvider();

        dAuthenticationProvider.setUserDetailsService(userdetailsservice);

        dAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return dAuthenticationProvider;
    }



    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider()
    {
        return new JwtAuthenticationProvider(jwtutility,userdetailsservice);
    }




    @Bean
    public AuthenticationManager authenticationManager()
    {
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider(),jwtAuthenticationProvider()));

    }

}
