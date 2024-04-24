package com.reto.obardales.resource;

import com.reto.obardales.commons.*;
import com.reto.obardales.exception.ApplicationValidationException;
import com.reto.obardales.resource.request.RequestPostUsers;
import com.reto.obardales.resource.response.ResponseGetUsers;
import com.reto.obardales.resource.response.ResponsePostUsers;
import com.reto.obardales.service.UserService;
import com.reto.obardales.service.dto.UserDto;
import com.reto.obardales.service.exception.UserDuplicateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Usuarios", tags = { "users" })
@RestController
@RequestMapping("/users")
public class UsersController {

    private Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Crea un Usuario", response = ResponsePostUsers.class)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody RequestPostUsers request) {

        try {

            UserDto userDto = this.userService.userRegister(request.toUser());
            logger.info(userDto.toString());

            ResponsePostUsers response = new ResponsePostUsers(userDto);

            return ResponseEntity.ok(response);
        } catch (ApplicationValidationException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage( e.errors().toString()));
        } catch (UserDuplicateException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage( e.getFacingMessage()));
        }

    }

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseGetUsers>> get() {

        List<UserDto> usersDto = this.userService.getAll();

        List<ResponseGetUsers> response = usersDto.stream()
                .map(ResponseGetUsers::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
