package com.reto.obardales.commons;

import com.reto.obardales.service.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTHelper {

    private final String jwtSecret;

    private static final long EXPIRATION_MILLIS = 24 * 60 * 60 * 1000; //24 hours

    public JWTHelper(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String generateToken(UserDto userDto, LocalDateTime localDateTime) {
        Date current = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date after24h = new Date(current.getTime() + EXPIRATION_MILLIS);
        return Jwts.builder().setClaims(new HashMap<>())
                .setSubject(userDto.getEmail())
                .setIssuedAt(current)
                .setExpiration(after24h)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

}
