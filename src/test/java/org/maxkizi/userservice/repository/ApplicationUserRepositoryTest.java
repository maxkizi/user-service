package org.maxkizi.userservice.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.userservice.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;

class ApplicationUserRepositoryTest extends BaseIntegrationTest {

    private final ApplicationUserRepository repository;
    private final TestDataProvider testDataProvider;

    @Autowired
    public ApplicationUserRepositoryTest(ApplicationUserRepository repository, TestDataProvider testDataProvider) {
        this.repository = repository;
        this.testDataProvider = testDataProvider;
    }

    @AfterEach
    void deleteAll() {
        repository.deleteAll();
    }

    @Test
    void attributeConverterTest() {
        ApplicationUser userToSave = testDataProvider.buildTestUser(1);
        ApplicationUser savedUser = repository.findById(repository.save(userToSave).getId()).get();
        userToSave.setId(savedUser.getId());
        Assertions.assertEquals(userToSave, savedUser);
    }
}