package org.shakti.uberauthservice.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.shakti.uberauthservice.Exceptions.CustomError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements CommandLineRunner {
    @Value("${jwt.expire}")
    private int EXPIRE;

    @Value("${jwt.secret}")
    private String SECRET;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    private String createToken(Map<String, Object> payload,String email) {
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(new Date().getTime() + EXPIRE*1000L))
                .subject(email)
                .signWith(getSecretKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
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

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String extractEmail(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    private boolean validateToken(String token, String email) {
        return (!isTokenExpired(token) && extractEmail(token).equals(email));
    }


    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> payload = Map.of(
                "email","shakti@gmail.com",
                "name","shakti"
        );
        String username = "shakti10128";
        String token = createToken(payload,username);
        System.out.println(token);
    }
}
