package com.reto.obardales.commons;

import com.reto.obardales.commons.PasswordHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordHelperTest {

    private PasswordHelper passwordHelper;

    @BeforeAll
    public void setup() {
        this.passwordHelper = new PasswordHelper(new Argon2PasswordEncoder());
    }

    @Test
    public void whenHashGeneratedMatches() {
        String myPassword = "ThisIsMyH4rdP455w0rd!";

        String pwdHash = this.passwordHelper.hash(myPassword);

        boolean result = this.passwordHelper.matches(myPassword, pwdHash);

        assertTrue(result);
    }

    @Test
    public void whenHashGeneratedNoMatches() {
        String myPassword = "ThisIsMyH4rdP455w0rd!";

        String pwdHash = this.passwordHelper.hash(myPassword);

        boolean result = this.passwordHelper.matches("another", pwdHash);

        assertFalse(result);
    }

}