package com.UserService.UserService.Utility;


import com.UserService.UserService.Entity.UserAuthEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.security.Keys;

@Component
public class JWtUtility
{
    private static final String SECRET_KEY = "your-secure-secret-key-min-32bytes";
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

//    public JWTUtility()
//    {
//
//    }

    public String generateToken(String Username, long expiryMinutes, UserAuthEntity userauth)
    {
        HashMap<String, Object> TokenObject = new HashMap<String, Object>();
        TokenObject.put("userId",userauth.getId());
        return Jwts.builder()
                .setClaims(TokenObject)
                .setSubject(Username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiryMinutes*60*1000))
                .signWith(key,SignatureAlgorithm.HS256).compact();
    }
    public String validateandExtractUsername(String token) {
        try {
            return Jwts.parser().setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}

