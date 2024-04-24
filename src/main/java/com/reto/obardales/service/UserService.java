package com.reto.obardales.service;

import com.reto.obardales.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserDto userRegister(UserDto user);

    public List<UserDto> getAll();

}
