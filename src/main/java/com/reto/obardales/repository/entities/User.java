package com.reto.obardales.repository.entities;

import com.reto.obardales.service.dto.UserDto;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Phone> phones;


    public User(UserDto userDto) {
        this.setName(userDto.getName());
        this.setEmail(userDto.getEmail());
        this.setPassword(userDto.getPassword());
        if (userDto.getPhones() != null) {
            this.setPhones(userDto.getPhones().stream()
                .map(x -> {
                    Phone phone = new Phone(x);
                    phone.setUser(this);
                    return phone;})
                .collect(Collectors.toList()));
        }
    }

    @PrePersist
    private void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public User() {
    }

    public String getId() {
        return id;
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
