package com.reto.obardales.service.validator;

import com.reto.obardales.service.dto.UserDto;
import com.reto.obardales.service.validator.UserRegisterValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRegisterValidatorTest {

    private ValidatorFactory validatorFactory;

    private Validator validator;

    @BeforeAll
    public void setup() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    @Test
    public void whenAllIsCorrect() {
        UserDto userDto = new UserDto();
        userDto.setName("myName");
        userDto.setEmail("local@domain.com");
        userDto.setPassword("P4sw00rd!x");

        Set<ConstraintViolation<UserDto>> violations =
                this.validator.validate(userDto, UserRegisterValidator.class);

        assertEquals(0, violations.size());
    }

    /*
    @Test
    public void email() {
        String value = ".h.-o_la23";

        assert Pattern.matches(Cte.PATTERN_PASSWORD, value);
    }
    */

    @Test
    public void digit() {
        String pattern = ".*[0-9].*";
        String value = "hunter222!Xxxx1";

        assert Pattern.matches(pattern, value);
    }

}