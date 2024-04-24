package com.reto.obardales.service.impl;

import com.reto.obardales.commons.JWTHelper;
import com.reto.obardales.commons.MessageHelper;
import com.reto.obardales.commons.PasswordHelper;
import com.reto.obardales.service.UserTestHelper;
import com.reto.obardales.repository.UserRepository;
import com.reto.obardales.repository.entities.User;
import com.reto.obardales.service.dto.UserDto;
import com.reto.obardales.service.exception.UserDuplicateException;
import com.reto.obardales.service.validator.UserRegisterValidator;
import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    private UserServiceImpl userService;

    private UserRepository userRepository;

    private LocalValidatorFactoryBean validator;

    private MessageHelper messageHelper;

    private JWTHelper jwtHelper;

    private PasswordHelper passwordHelper;

    @BeforeAll
    public void setup() {
        this.userRepository = mock(UserRepository.class);

        this.validator = new LocalValidatorFactoryBean();
        this.validator.setProviderClass(HibernateValidator.class);
        this.validator.afterPropertiesSet();

        this.messageHelper = mock(MessageHelper.class);
        this.jwtHelper = mock(JWTHelper.class);
        this.passwordHelper = mock(PasswordHelper.class);
        this.userService = new UserServiceImpl(userRepository, validator,
                messageHelper, jwtHelper, passwordHelper);

        when(this.passwordHelper.hash(any()))
                .thenReturn(Base64.getEncoder().encodeToString("random".getBytes(StandardCharsets.UTF_8)));

        when(this.jwtHelper.generateToken(any(), any()))
                .thenReturn("token-mock");
    }


    @Test
    public void whenRegisterIsSuccess() {
        UserDto userDto = UserTestHelper.forRegister();
        userDto.setEmail("juan.register.success@rodriguez.org");

        when(this.userRepository.findByEmail(userDto.getEmail()))
                .thenReturn(Optional.empty());

        this.userService.userRegister(userDto);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(this.userRepository).save(argument.capture());

        assertEquals(userDto.getEmail(), argument.getValue().getEmail());
    }

    @Test
    public void whenUserDuplicate() {
        UserDto userDto = UserTestHelper.forRegister();
        UserDto userDto2 = UserTestHelper.forRegister();

        when(this.userRepository.findByEmail(userDto.getEmail()))
                .thenReturn(Optional.ofNullable(new User(userDto2)));

        assertThrows(UserDuplicateException.class,
                () -> this.userService.userRegister(userDto));
    }

    @Test
    public void whenNameIsNull() {
        UserDto userDto = UserTestHelper.forRegister();
        userDto.setName(null);

        Set<ConstraintViolation<Object>> violations =
                this.validator.validate(userDto, UserRegisterValidator.class);

        assertEquals(1, violations.size());
    }

    @Test
    public void whenNameIsMax() {
        UserDto userDto = UserTestHelper.forRegister();
        StringBuilder b = new StringBuilder();
        IntStream.rangeClosed(0, 150)
                .mapToObj(i -> "x")
                .forEach(b::append);

        userDto.setName(b.toString());

        Set<ConstraintViolation<Object>> violations =
                this.validator.validate(userDto, UserRegisterValidator.class);

        assertEquals(1, violations.size());
    }

    @Test
    public void whenEmailIsNull() {
        UserDto userDto = UserTestHelper.forRegister();
        userDto.setEmail(null);

        Set<ConstraintViolation<Object>> violations =
                this.validator.validate(userDto, UserRegisterValidator.class);

        assertEquals(1, violations.size());
    }

    @Test
    public void whenEmailIsBadFormat() {
        UserDto userDto = UserTestHelper.forRegister();
        userDto.setEmail("juanrodriguez.org");

        Set<ConstraintViolation<Object>> violations =
                this.validator.validate(userDto, UserRegisterValidator.class);

        assertEquals(1, violations.size());
    }

    @Test
    public void whenPasswordIsNull() {
        UserDto userDto = UserTestHelper.forRegister();
        userDto.setPassword(null);

        Set<ConstraintViolation<Object>> violations =
                this.validator.validate(userDto, UserRegisterValidator.class);

        assertEquals(1, violations.size());
    }

    @Test
    public void whenPasswordIsBadFormat() {
        UserDto userDto = UserTestHelper.forRegister();
        userDto.setPassword("basic");

        Set<ConstraintViolation<Object>> violations =
                this.validator.validate(userDto, UserRegisterValidator.class);

        assertEquals(1, violations.size());
    }
}