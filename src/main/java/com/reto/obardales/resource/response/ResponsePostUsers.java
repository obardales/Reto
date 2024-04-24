package com.reto.obardales.resource.response;

import com.reto.obardales.service.dto.UserDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResponsePostUsers implements Serializable {

    private String id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isactive;


    public ResponsePostUsers(UserDto userDto) {
        this.setId(userDto.getId());
        this.setCreated(userDto.getCreated());
        this.setModified(userDto.getModified());
        this.setLastLogin(userDto.getLastLogin());
        this.setToken(userDto.getToken());
        this.setIsactive(userDto.getIsactive());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}
