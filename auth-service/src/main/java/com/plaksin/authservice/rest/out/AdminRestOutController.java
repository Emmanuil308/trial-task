package com.plaksin.authservice.rest.out;

import com.plaksin.authservice.dto.SavedUserDto;
import com.plaksin.authservice.exception.ConstraintException;
import com.plaksin.authservice.model.User;
import com.plaksin.authservice.model.response.Response;
import com.plaksin.authservice.service.UserDtoService;
import com.plaksin.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
public class AdminRestOutController {

    private final UserDtoService userDtoService;
    private final UserService userService;


    @PostMapping("/users")
    public Response<SavedUserDto> saveNewUser(@RequestBody User user) {
        if (userService.checkUserEmail(user.getEmail())) {
            throw new ConstraintException("User with email " + user.getEmail() + " exist in data base");
        }
        return Response.ok(userDtoService.saveNewUser(user));
    }
}
