package org.maxkizi.userservice;

import org.maxkizi.userservice.dto.ApplicationUserDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataProvider {

    public ApplicationUser buildTestUser(int index, LocalDate dateOfBirth) {
        return ApplicationUser.builder()
                .username("username" + index)
                .dateOfBirth(dateOfBirth)
                .userStatus(UserStatus.NONE)
                .email("email" + index)
                .build();
    }

    public ApplicationUserDto buildTestUserDto(int index) {
        return ApplicationUserDto.builder()
                .username("username" + index)
                .dateOfBirth("23.04.1994")
                .email("email" + index)
                .build();
    }
}
