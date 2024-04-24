package com.reto.obardales.service.impl;

import com.reto.obardales.exception.ApplicationValidationException;
import com.reto.obardales.commons.Cte;
import com.reto.obardales.commons.JWTHelper;
import com.reto.obardales.commons.MessageHelper;
import com.reto.obardales.commons.PasswordHelper;
import com.reto.obardales.repository.UserRepository;
import com.reto.obardales.repository.entities.User;
import com.reto.obardales.service.UserService;
import com.reto.obardales.service.dto.UserDto;
import com.reto.obardales.service.exception.UserDuplicateException;
import com.reto.obardales.service.validator.UserRegisterValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository repository;

    private final LocalValidatorFactoryBean validator;

    private final MessageHelper messageHelper;

    private final JWTHelper jwtHelper;

    private final PasswordHelper passwordHelper;

    public UserServiceImpl(
            UserRepository repository,
            LocalValidatorFactoryBean validator,
            MessageHelper messageHelper,
            JWTHelper jwtHelper,
            PasswordHelper passwordHelper) {
        this.repository = repository;
        this.validator = validator;
        this.messageHelper = messageHelper;
        this.jwtHelper = jwtHelper;
        this.passwordHelper = passwordHelper;
    }

    @Override
    public UserDto userRegister(UserDto user) {

        validateRegister(user);

        this.repository.findByEmail(user.getEmail())
                .map(User::getEmail)
                .ifPresent(x -> { throw new UserDuplicateException(x,
                        messageHelper.get(Cte.Message.APP_EXCEPTION_USER_DUPLICATE));});

        LocalDateTime created = LocalDateTime.now();
        user.setCreated(created);
        user.setModified(created);
        user.setLastLogin(created);
        user.setIsactive(Boolean.TRUE);
        user.setPassword(this.passwordHelper.hash(user.getPassword()));
        user.setToken(this.jwtHelper.generateToken(user, created));

        User userEntity = new User(user);

        this.repository.save(userEntity);

        user.setId(userEntity.getId());

        return user;
    }

    private void validateRegister(UserDto user) {
        Set<ConstraintViolation<Object>> violations =
                validator.validate(user, UserRegisterValidator.class);

        if(!violations.isEmpty()) {
            throw new ApplicationValidationException(
                    messageHelper.get(Cte.Message.USER_REGISTER_VALIDATION),
                    violations);
        }

    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = this.repository.findAll();
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }


}