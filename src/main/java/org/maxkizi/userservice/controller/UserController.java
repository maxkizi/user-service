package org.maxkizi.userservice.controller;

import org.maxkizi.userservice.dto.ApplicationUserDto;
import org.maxkizi.userservice.dto.ApplicationUserStatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.maxkizi.userservice.controller.Controllers.USER;
import static org.maxkizi.userservice.controller.Controllers.USER_BY_ID;

@RestController
public class UserController {

    @PostMapping(USER)
    public ApplicationUserDto createUser(ApplicationUserDto applicationUserDto) {
        return null;
    }

    @GetMapping(USER_BY_ID)
    public ApplicationUserDto findById(@PathVariable(name = "id") Long id) {
        return null;
    }

    @PutMapping(USER_BY_ID)
    public ApplicationUserStatusDto updateStatus(@PathVariable(name = "id") Long id,
                                                 @RequestParam(name = "status") String status) {
        return null;
    }
}
