package com.example.todo.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 1 * 60 * 60 * 100;

    @Value("${app.secret.key}")
    private String secretKey;

    public String generateAccessKey(CustomUserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getUser().getRole().getValue())
                .setIssuer("Tony")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Jwt expired", e);
        } catch (MalformedJwtException e) {
            log.error("Jwt is invalid", e);
        } catch (IllegalArgumentException e) {
            log.error("Jwt is null, empty or only white space", e);
        } catch (SignatureException e) {
            log.error("Signature validation fails", e);
        } catch (UnsupportedJwtException e) {
            log.error("Jwt is not supported", e);
        }
        return false;
    }

    public Claims parseClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }


}
