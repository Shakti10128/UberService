package org.shakti.uberauthservice.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.shakti.uberauthservice.Exceptions.CustomError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl{
    @Value("${jwt.expire}")
    private int EXPIRE;

    @Value("${jwt.secret}")
    private String SECRET;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String createToken(Map<String, Object> payload,String email) {
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(new Date().getTime() + EXPIRE*1000L))
                .subject(email)
                .signWith(getSecretKey())
                .compact();
    }

    public String createToken(String email) {
        return createToken(new HashMap<>(), email);
    }

    public Claims extractAllClaims(String token) {
        try{
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        catch(Exception e){
            throw new CustomError("Invalid JWT token", HttpStatus.BAD_REQUEST);
        }
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractEmail(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    public boolean validateToken(String token, String email) {
        return (!isTokenExpired(token) && extractEmail(token).equals(email));
    }
}
