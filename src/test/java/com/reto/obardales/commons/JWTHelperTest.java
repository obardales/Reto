package com.reto.obardales.commons;

import com.reto.obardales.commons.JWTHelper;
import com.reto.obardales.service.dto.UserDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JWTHelperTest {

    private JWTHelper jwtHelper;

    private String secret;

    @BeforeAll
    public void setup() {
        this.secret = "mysecret";
        this.jwtHelper = new JWTHelper(this.secret);
    }

    @Test
    public void whenTokenIsGeneratedCorrectly() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJleHAiOjE2NDg4NzkwOTMsImlhdCI6MTY0ODc5MjY5M30.ARgovX2EazLxm26-8i6Q_35w0JGLGED-DNIeESdC3_gXz0ukUlckl9R17aMGj7uJZCCB68-Hrm0LZYfaHdkJ4Q";

        long createdInMillis = 1648792693000L;
        Date created = new Date(createdInMillis);
        UserDto userDto = new UserDto();
        userDto.setEmail("juan@rodriguez.org");

        LocalDateTime parsed = created.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String tokenGenerated = this.jwtHelper.generateToken(userDto, parsed);

        assertEquals(token, tokenGenerated);
    }


    @Test
    public void whenTokenIsExpired() {
        LocalDateTime twoDaysAgo = LocalDateTime.now().minusDays(1);
        UserDto userDto = new UserDto();
        userDto.setEmail("juan@rodriguez.org");

        String token = this.jwtHelper.generateToken(userDto, twoDaysAgo);

        assertThrows(ExpiredJwtException.class,
                () -> this.jwtHelper.getUsernameFromToken(token));
    }

    @Test
    public void whenTokenGetUserSuccessfully() {
        LocalDateTime twoDaysAgo = LocalDateTime.now();
        UserDto userDto = new UserDto();
        userDto.setEmail("juan@rodriguez.org");

        String token = this.jwtHelper.generateToken(userDto, twoDaysAgo);

        String userEmail = this.jwtHelper.getUsernameFromToken(token);

        assertEquals(userDto.getEmail(), userEmail);
    }

    @Test
    public void whenTokenIsNotExpired() {
        UserDto userDto = new UserDto();
        userDto.setEmail("juan@rodriguez.org");

        String token = this.jwtHelper.generateToken(userDto, LocalDateTime.now());

        boolean isValid = this.jwtHelper.validateToken(token);

        assertTrue(isValid);
    }
}