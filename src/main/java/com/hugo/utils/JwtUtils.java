package com.hugo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "HugoWebJwt";
    private static long expiration = 43200000;

    public static String generateJWT(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public static Claims parseJWT(String Jwt) {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(Jwt)
                .getBody();
    }
}
