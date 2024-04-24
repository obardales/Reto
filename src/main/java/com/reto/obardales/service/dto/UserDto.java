package com.reto.obardales.service.dto;

import com.reto.obardales.commons.Cte;
import com.reto.obardales.repository.entities.User;
import com.reto.obardales.service.validator.UserRegisterValidator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    private String id;

    @NotEmpty(groups = UserRegisterValidator.class,
            message = Cte.MSG_USER_NAME_NOTEMPTY)
    @Size(max = 150,
            groups = UserRegisterValidator.class,
            message = Cte.MSG_USER_NAME_MAX)
    private String name;

    @NotEmpty(groups = UserRegisterValidator.class,
            message = Cte.MSG_USER_EMAIL_NOTEMPTY)
    @Pattern(regexp = Cte.PATTERN_EMAIL,
            groups = UserRegisterValidator.class,
            message = Cte.MSG_USER_EMAIL_MATCH)
    private String email;

    @NotEmpty(groups = UserRegisterValidator.class,
            message = Cte.MSG_USER_PWD_NOTEMPTY)
    @Pattern(regexp = Cte.PATTERN_PASSWORD,
            groups = UserRegisterValidator.class,
            message = Cte.MSG_USER_PWD_MATCH)
    private String password;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private Boolean isactive;
    private String token;

    private List<PhoneDto> phones;

    public UserDto() {
    }

    public UserDto(
            String id,
            String name,
            String email,
            String password,
            LocalDateTime created,
            LocalDateTime modified,
            LocalDateTime lastLogin,
            Boolean isactive,
            String token,
            List<PhoneDto> phones) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.isactive = isactive;
        this.token = token;
        this.phones = phones;
    }

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                null,
                null,
                null,
                true,
                null,
                user.getPhones().stream()
                        .map(PhoneDto::from)
                        .collect(Collectors.toList()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
