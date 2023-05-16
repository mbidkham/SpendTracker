package com.snapp.spendtracker.util;

import com.snapp.spendtracker.infrastructure.domain.UserInformationEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTokenUtil {

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    public static String generateToken(String userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
            .compact();
    }

    public static boolean validateToken(String token, UserInformationEntity userDetails) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            var username = extractUsername(token);
            return (username.equals(userDetails.getUserName()) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractUsername(String jwtToken) {
        Claims claims = Jwts
            .parserBuilder()
            .setSigningKey(JwtTokenUtil.SECRET_KEY)
            .build()
            .parseClaimsJws(jwtToken)
            .getBody();
        return claims.getSubject();
    }
    public static boolean isTokenExpired(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();

        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }
}
