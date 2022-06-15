package org.maxkizi.userservice;

import org.maxkizi.userservice.dto.ApplicationUserDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TestDataProvider {
    @Value("${adultAge}")
    public Integer adultAge;

    public ApplicationUser buildTestUser(int index, LocalDate dateOfBirth) {
        return ApplicationUser.builder()
                .username("username" + index)
                .dateOfBirth(dateOfBirth)
                .userStatus(UserStatus.NONE)
                .email("email" + index)
                .build();
    }

    public List<ApplicationUser> buildThreeAdultsAndOneUnderageUsers() {
        return List.of(
                ApplicationUser.builder()
                        .username("username1")
                        .dateOfBirth(LocalDate.now())
                        .userStatus(UserStatus.NONE)
                        .age(0)
                        .email("email1")
                        .build(),
                ApplicationUser.builder()
                        .username("username2")
                        .dateOfBirth(LocalDate.now().minusYears(adultAge))
                        .age(18)
                        .userStatus(UserStatus.NONE)
                        .email("email2")
                        .build(),
                ApplicationUser.builder()
                        .username("username3")
                        .dateOfBirth(LocalDate.now().minusYears(adultAge))
                        .userStatus(UserStatus.NONE)
                        .age(18)
                        .email("email3")
                        .build(),
                ApplicationUser.builder()
                        .username("username4")
                        .dateOfBirth(LocalDate.now().minusYears(adultAge + 10))
                        .userStatus(UserStatus.NONE)
                        .email("email4")
                        .age(28)
                        .build()
        );
    }

    public ApplicationUserDto buildTestUserDto(int index) {
        return ApplicationUserDto.builder()
                .username("username" + index)
                .dateOfBirth("23.04.1994")
                .email("email" + index)
                .build();
    }
}
