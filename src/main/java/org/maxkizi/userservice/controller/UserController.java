package org.maxkizi.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.userservice.converter.ApplicationUserConverter;
import org.maxkizi.userservice.dto.ApplicationUserDto;
import org.maxkizi.userservice.dto.ApplicationUserStatusDto;
import org.maxkizi.userservice.service.ApplicationUserService;
import org.maxkizi.userservice.utils.UserStatusUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import static org.maxkizi.userservice.controller.Controllers.USER;
import static org.maxkizi.userservice.controller.Controllers.USER_BY_ID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ApplicationUserService userService;
    private final ApplicationUserConverter converter;

    @PostMapping(USER)
    public ApplicationUserDto createUser(@Valid @RequestBody ApplicationUserDto applicationUserDto) {

        return converter.toDto(userService.create(converter.toEntity(applicationUserDto)));
    }

    @GetMapping(USER_BY_ID)
    public ApplicationUserDto findById(@PathVariable(name = "id") Long id) {
        return converter.toDto(userService.finnById(id));
    }

    @PutMapping(USER_BY_ID)
    public ApplicationUserStatusDto updateStatus(@PathVariable(name = "id") Long id,
                                                 @RequestParam(name = "status") String status) {

        return userService.updateStatus(id, UserStatusUtil.convertAndValidate(status));
    }
}
