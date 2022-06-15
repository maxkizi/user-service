package org.maxkizi.userservice.repository;

import org.maxkizi.userservice.enumeration.UserStatus;
import org.maxkizi.userservice.model.ApplicationUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationUserRepository extends BaseRepository<ApplicationUser, Long> {

    @Query(value = "update application_user set user_status = :userStatus where id = :id", nativeQuery = true)
    @Modifying
    void updateUserStatus(@Param("userStatus") String userStatus, @Param("id") Long id);

    @Query(value = "select * from application_user where age >= :age and user_status = :userStatus", nativeQuery = true)
    List<ApplicationUser> findAdultApplicationUsersByStatus(@Param("userStatus") String userStatus, @Param("age") int age);

    @Query(value = "select * from application_user where age < :age and user_status = :userStatus", nativeQuery = true)
    List<ApplicationUser> findUnderAgeApplicationUsersByStatus(@Param("userStatus") String userStatus, @Param("age") int age);


    List<ApplicationUser> findApplicationUsersByUserStatus(UserStatus userStatus);
}
