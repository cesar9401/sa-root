package com.cesar31.root.infrastructure.adapters.input.rest.security;

import com.cesar31.root.infrastructure.adapters.input.rest.dto.JwtResDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${security.jwt.ttlMillis}")
    private Long TTL_MILLIS;

    @Value("${security.jwt.issuer}")
    private String ISSUER;

    public JwtResDto generateToken(UserDetails userDetails, String org) {
        var ms = new Date()
                .toInstant()
                .plus(TTL_MILLIS, ChronoUnit.MILLIS)
                .toEpochMilli();

        var token = Jwts
                .builder()
                .claims(Map.of("authorities", userDetails.getAuthorities()))
                .claim("org", org)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(ms))
                .issuer(ISSUER)
                .signWith(getSecretKey())
                .compact();

        return new JwtResDto(token, ms / 1000);
    }

    public String getUsername(String token) {
        var claims = extractClaims(token);
        return claims.getSubject();
    }

    public boolean isValid(String token) {
        var claims = extractClaims(token);
        var expiration = claims.getExpiration();
        return new Date().before(expiration);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
