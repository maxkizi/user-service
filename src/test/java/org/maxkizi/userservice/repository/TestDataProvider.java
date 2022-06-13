package org.maxkizi.userservice.repository;

import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataProvider {

    public ApplicationUser buildTestUser(int index){
        return ApplicationUser.builder()
                .username("username" + index)
                .age(1)
                .userStatus(UserStatus.OFFLINE)
                .dateOfBirth(LocalDate.now())
                .email("email" + index)
                .build();
    }
}
