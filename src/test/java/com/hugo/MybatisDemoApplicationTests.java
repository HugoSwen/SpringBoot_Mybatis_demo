package com.hugo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MybatisDemoApplicationTests {

    @Test
    void testJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", '1');
        claims.put("name", "hugo");
        String HugoWebJWT = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "HugoWebJwt")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 120 * 1000))
                .compact();

        System.out.println(HugoWebJWT);
    }

    @Test
    void testParseJWT() {
        Map<String, Object> hugoWebJwt = Jwts.parser()
                .setSigningKey("HugoWebJwt")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiaHVnbyIsImlkIjoiMSIsImV4cCI6MTY5MzEyNDY0MX0.FTwj-QXbr0o0eYVHV7edd_NISblvEQRl-aJ2oQ4NEcU")
                .getBody();
        System.out.println(hugoWebJwt);
    }
}
