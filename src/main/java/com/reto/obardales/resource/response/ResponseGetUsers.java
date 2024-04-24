package com.reto.obardales.resource.response;

import com.reto.obardales.service.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseGetUsers {

    private String id;
    private String name;
    private String email;
    private String password;
    private String token;
    private List<ResponseGetUsersPhone> phones;

    public ResponseGetUsers() {
    }

    public ResponseGetUsers(
            String id,
            String name,
            String email,
            String password,
            String token,
            List<ResponseGetUsersPhone> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public static ResponseGetUsers from(UserDto userDto) {
        return new ResponseGetUsers(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getToken(),
                userDto.getPhones().stream()
                        .map(ResponseGetUsersPhone::from)
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

    public List<ResponseGetUsersPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<ResponseGetUsersPhone> phones) {
        this.phones = phones;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
