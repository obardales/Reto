package com.reto.obardales.resource.request;

import com.reto.obardales.service.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class RequestPostUsers {

    private String name;
    private String email;
    private String password;
    private List<RequestPostUsersPhone> phones;

    public UserDto toUser() {
        UserDto userDto = new UserDto();
        userDto.setName(this.getName());
        userDto.setEmail(this.getEmail());
        userDto.setPassword(this.getPassword());
        if (this.getPhones() != null) {
            userDto.setPhones(this.getPhones().stream()
                    .map(RequestPostUsersPhone::toPhone)
                    .collect(Collectors.toList()));

        }
        return userDto;
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

    public List<RequestPostUsersPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<RequestPostUsersPhone> phones) {
        this.phones = phones;
    }
}
