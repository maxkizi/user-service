package org.maxkizi.userservice.repository;

import org.maxkizi.userservice.model.ApplicationUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationUserRepository extends BaseRepository<ApplicationUser, Long> {

    @Query(value = "update application_user set user_status = :userStatus where id = :id", nativeQuery = true)
    @Modifying
    void updateUserStatus(String userStatus, Long id);
}
