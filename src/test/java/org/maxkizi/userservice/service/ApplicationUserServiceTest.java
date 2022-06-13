package org.maxkizi.userservice.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.userservice.BaseIntegrationTest;
import org.maxkizi.userservice.TestDataProvider;
import org.maxkizi.userservice.dto.ApplicationUserStatusDto;
import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;
import org.maxkizi.userservice.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

class ApplicationUserServiceTest extends BaseIntegrationTest {

    private final TestDataProvider testDataProvider;
    private final ApplicationUserRepository repository;
    private final ApplicationUserService service;

    @Autowired
    public ApplicationUserServiceTest(TestDataProvider testDataProvider, ApplicationUserRepository repository, ApplicationUserService service) {
        this.testDataProvider = testDataProvider;
        this.repository = repository;
        this.service = service;
    }

    @AfterEach
    void deleteAll() {
        repository.deleteAll();
        repository.flush();
    }

    @Test
    void createAndFindUser() {
        int expectedAge = 10;
        ApplicationUser userToSave = testDataProvider.buildTestUser(1, LocalDate.now().minusYears(expectedAge));
        Long id = service.create(userToSave).getId();
        ApplicationUser foundUser = service.finnById(id);
        foundUser.setId(id);
        userToSave.setAge(expectedAge);
        Assertions.assertEquals(userToSave, foundUser);
    }

    @Test
    void updateStatus() {
        ApplicationUser userToSave = testDataProvider.buildTestUser(2, LocalDate.now());
        ApplicationUser savedUser = service.create(userToSave);
        Assertions.assertEquals(UserStatus.NONE, savedUser.getUserStatus());

        ApplicationUserStatusDto userStatusDto = service.updateStatus(savedUser.getId(), UserStatus.ONLINE);
        Assertions.assertEquals(UserStatus.ONLINE.getTextStatus(), userStatusDto.getNewStatus());
        Assertions.assertEquals(UserStatus.NONE.getTextStatus(), userStatusDto.getPreviousStatus());
        Assertions.assertEquals(savedUser.getId(), userStatusDto.getId());
    }
}