package com.plaksin.authservice.rest.internal;

import com.plaksin.authservice.dto.SavedUserDto;
import com.plaksin.authservice.service.UserDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internal")
public class UserInternalRestController {

    private final UserDtoService userDtoService;


    @GetMapping("/users/{userId}")
    public SavedUserDto getUserById(@PathVariable("userId") Long userId) {
        return userDtoService.getUserById(userId);
    }
}
